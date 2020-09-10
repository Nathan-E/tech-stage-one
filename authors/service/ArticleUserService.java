package service;

import java.util.List;

public interface ArticleUserService {


    List<String> getUsernames(int threshold);

    String getUsernameWithHighestCommentCount();

    List<String> getUsernamesSortedByRecordDate(int threshold);


}
