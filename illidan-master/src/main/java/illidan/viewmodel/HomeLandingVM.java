package illidan.viewmodel;

import illidan.model.*;

import java.util.ArrayList;

public class HomeLandingVM {
    private ArrayList<ProductDetailModel> listProductsByIOS;
    private ArrayList<ProductDetailModel> listProductsByAndroid;
    private ArrayList<OrderDataModel> listOrders;

    public ArrayList<ProductDetailModel> getListProductsByIOS() {
        return listProductsByIOS;
    }

    public void setListProductsByIOS(ArrayList<ProductDetailModel> listProductsByIOS) {
        this.listProductsByIOS = listProductsByIOS;
    }

    public ArrayList<ProductDetailModel> getListProductsByAndroid() {
        return listProductsByAndroid;
    }

    public void setListProductsByAndroid(ArrayList<ProductDetailModel> listProductsByAndroid) {
        this.listProductsByAndroid = listProductsByAndroid;
    }

    public ArrayList<OrderDataModel> getListOrders() {
        return listOrders;
    }

    public void setListOrders(ArrayList<OrderDataModel> listOrders) {
        this.listOrders = listOrders;
    }
}
