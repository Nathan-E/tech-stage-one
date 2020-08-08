package service.Implementation;



import dtos.ApiResponseDTO;
import dtos.ArticleUserDTO;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import service.ArticleUserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ArticleUserServiceImpl implements ArticleUserService {

    private static final String baseUrl   = "https://jsonmock.hackerrank.com";
    private static final String basePath  = "/api/article_users/search?page=%s";
    static List<ArticleUserDTO> apiData;

    public ArticleUserServiceImpl() {
        populateArticleUsers();
    }

    @Override
    public List<String> getUsernames(int threshold) {
        List<String> thresholdUsers = apiData.stream().filter(
                user-> user.getSubmission_count() >= threshold).map(u-> u.getUsername()).collect(Collectors.toList());

        return thresholdUsers;
    }

    @Override
    public String getUsernameWithHighestCommentCount() {
        ArticleUserDTO articleUsersDTO = apiData.stream().max(Comparator.comparing(ArticleUserDTO :: getComment_count ))
                .orElse(null);

        if(articleUsersDTO==null){
            return null;
        }else{
            return articleUsersDTO.getUsername();
        }
    }

    @Override
    public List<String> getUsernamesSortedByRecordDate(int threshold) {

        List<String> sortedUsernamesByRecDate = apiData.stream().filter(
                user-> user.getCreated_at() >= threshold)
                .sorted(Comparator.comparingLong(a -> a.getCreated_at()))
                .map(u-> u.getUsername())
                .collect(Collectors.toList());

        return sortedUsernamesByRecDate;
    }


    private List<ArticleUserDTO> populateArticleUsers(){

        apiData = new ArrayList<>();

        String fullUrl = String.format(baseUrl+basePath,1);
        String resp = makeHttpGetRequest(fullUrl);


        ApiResponseDTO apiResponse;
        try {

            apiResponse = new ObjectMapper().readValue(resp, ApiResponseDTO.class);
            if(apiResponse!=null && apiResponse.getData().size()>0){
                apiData.addAll(apiResponse.getData());

                for(int i=1;i<apiResponse.getTotal_pages();i++){

                    fullUrl = String.format(baseUrl+basePath,i+1);
                    resp = makeHttpGetRequest(fullUrl);
                    apiResponse = new ObjectMapper().readValue(resp, ApiResponseDTO.class);
                    apiData.addAll(apiResponse.getData());

                }
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return apiData;
    }


    private String makeHttpGetRequest (String requestUrl){
        System.out.println("making url call to :: "+requestUrl);
        String httpResponse="";
        try {
            URL url = new URL(requestUrl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            int responseCode = con.getResponseCode();
            System.out.println("Response Code : " + responseCode);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            //print in String
            httpResponse = response.toString();

        }catch (MalformedURLException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }

        return httpResponse;

    }
}
