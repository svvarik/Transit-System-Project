package GUI.AdminScreens.AdminManageUsersScreen;

import AdminUsers.AdminUser;
import FareSystem.Card;
import GUI.HelperClasses.ControllerHelper;
import GUI.GeneralControllerClass.GeneralControllerScreen;
import TransitUsers.CardHolder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;


public class AdminManageUserSceneController extends GeneralControllerScreen {

    @FXML
    TextField userEmail;
    @FXML
    Label userEmailSuccess;
    @FXML
    Label userStatus;
    @FXML
    Button backButton;
    @FXML
    Button viewCardsButton;
    @FXML
    Button removeBanUserButton;
    @FXML
    Button banUserButton;
    @FXML
    ListView cardListView;
    @FXML
    Button suspendCardButton;
    @FXML
    Button unsuspendCardButton;
    @FXML
    Button deleteCardButton;
    @FXML
    Label userBeingViewed;

    private AdminUser adminUser;

    private CardHolder currentlyViewingCH;

    @FXML
    public void handleBackButton(ActionEvent e){
        this.getControllerHelper().openSameWindow("/GUI/AdminScreens/AdminMainScene.fxml", this.getTransitSystem(), e);
    }

    @FXML
    public void handleViewCardsButton(ActionEvent e){
        CardHolder chViewed = this.getTransitSystem().getCardHolders().findCardHolder(this.userEmail.getText());
        if (chViewed != null){
            this.userBeingViewed.setText(chViewed.getEmail());
            this.currentlyViewingCH = chViewed;
            this.cardListView.setItems(chViewed.getObservableCards());
            this.userEmail.clear();
            if (chViewed.isBanned()){
                this.userStatus.setText("Banned");
            } else {
                this.userStatus.setText("Regular");
            }
            this.userEmailSuccess.setText("");
        } else {
            this.userEmailSuccess.setText("Invalid User");
        }
    }

    @FXML
    public void handleBanUserButton(ActionEvent e){
        CardHolder chViewed = this.getTransitSystem().getCardHolders().findCardHolder(this.userEmail.getText());
        if (chViewed != null){
            this.adminUser.banUser(chViewed);
            this.userStatus.setText("Banned");
            this.userEmailSuccess.setText("");
        } else {
            this.userEmailSuccess.setText("Invalid User");
        }
    }

    @FXML
    public void handleRemoveBanButton(ActionEvent e){
        CardHolder chViewed = this.getTransitSystem().getCardHolders().findCardHolder(this.userEmail.getText());
        if (chViewed != null){
            this.adminUser.unBanUser(chViewed);
            this.userStatus.setText("Regular");
            this.userEmailSuccess.setText("");
        } else {
            this.userEmailSuccess.setText("Invalid User");
        }
    }

    @FXML
    public void handleDeleteCardButton(ActionEvent e){
        if(this.currentlyViewingCH != null) {
            this.getTransitSystemInteractions().removeCard(this.currentlyViewingCH, (Card)
                    this.cardListView.getSelectionModel().getSelectedItem());
            this.cardListView.setItems(this.currentlyViewingCH.getObservableCards());
        }
    }

    @FXML
    public void handleSuspendCardButton(ActionEvent e){
        this.getTransitSystemInteractions().suspendCard((Card)
                this.cardListView.getSelectionModel().getSelectedItem());
        this.cardListView.setItems(this.currentlyViewingCH.getObservableCards());
    }

    public void handleUnsuspendCardButton(ActionEvent e){
        this.getTransitSystemInteractions().unSuspendCard((Card)
                this.cardListView.getSelectionModel().getSelectedItem());
        this.cardListView.setItems(this.currentlyViewingCH.getObservableCards());
    }

    public void setUpController(Object object){
        this.adminUser = (AdminUser) object;
    }

}