package nftspy.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import nftspy.database.DatabaseHelper;
import nftspy.database.SQLiteHelper;
import nftspy.post.Post;
import nftspy.utils.Config;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class NewsfeedController extends Controller implements Initializable {
    private static final int NUMBER_OF_POSTS = 10;
    @FXML
    private Label content;

    @FXML
    private VBox newsfeedVBox;

    @FXML
    private Label hashtags;

    @FXML
    private Label title;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DatabaseHelper db = new SQLiteHelper("jdbc:sqlite:" + Config.DATABASE_PATH);
        try {
            List<Post> posts = db.getLatestPostLst(NUMBER_OF_POSTS);
            for (Post post : posts) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/resources/fxml/postcard.fxml"));
                VBox postCard = loader.load();
                PostCardController postCardController = loader.getController();
                postCardController.setData(post);
                newsfeedVBox.getChildren().add(postCard);
            }
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    void onHomeButtonClicked(ActionEvent event) {}
}
