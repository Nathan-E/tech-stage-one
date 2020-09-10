import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.*;
import service.ArticleUserService;
import service.Implementation.ArticleUserServiceImpl;

import java.util.List;

public class ArticleUsersTest {

	private ArticleUserService articleUserService;

	{
		articleUserService = new ArticleUserServiceImpl();
	}


	@Test
	public void WhenGetUsernames() throws Exception {

		List<String> articleUsers = articleUserService.getUsernames(0);
		Assertions.assertEquals(15, articleUsers.size());
	}

    @Test
    public void WhenGetUsernameWithHighestCommentCount() throws Exception {

        String highestCommentUser = articleUserService.getUsernameWithHighestCommentCount();
        Assertions.assertEquals("guelo", highestCommentUser);
    }
    @Test
    public void WhenGetUsernamesSortedByRecordDate() throws Exception {

        List<String> articleUsers = articleUserService.getUsernamesSortedByRecordDate(1301039509);
        Assertions.assertEquals(8, articleUsers.size());
    }



}
