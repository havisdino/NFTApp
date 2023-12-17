package nftspy.gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import nftspy.post.Post;

import java.net.URL;
import java.util.ResourceBundle;

public class PostCardController {

    @FXML
    private Label content;

    @FXML
    private Label hashtags;

    @FXML
    private Label title;

    public void setData(Post post) {
        title.setText(post.getTitle());
        content.setText(post.getContent());
        hashtags.setText(String.join(" ", post.getTags()));
    }
}
