package view;

import manager.ProductManager;
import model.Product;
import validator.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuProduct {
    ProductManager productManager = new ProductManager();
    public void showMainMenu(){
        int choice;
        do {
            System.out.println("=====Menu Quản Lý Sản Phẩm =====\n1.Thêm mới sản phẩm.\n2.Sửa sản phẩm theo Id.\n3.Xóa sản phẩm theo id.\n" +
                    "4.Lấy ra tất cả sản phẩm.\n5.Tìm kiếm sản phẩm theo tên gần đúng.\n6.Nhập vào loại sản phẩm và lấy ra tất cả các sản phẩm cùng loại.\n" +
                    "7.Tìm kiếm sản phẩm theo Id.\n0.Thoát.");
            System.out.println("Xin mời nhập sự lựa chọn");
            choice = Validator.inputChoice();
            switch (choice){
                case  1:
                    showMenuAdd();
                    break;
                case  2:
                    showMenuEdit();
                    break;
                case 3:
                    showMenuDelete();
                    break;
                case 4:
                    showAll();
                    break;
                case 5:
                    showMenuShowNameProduct();
                    break;
                case 6:
                    showMenuProductSameType();
                    break;
                case 7:
                    showMenuSearchById();
                    break;
            }
        } while (choice != 0);

    }

    public void showMenuAdd(){
        System.out.println("=======Đây là mục thêm mới sản phẩm =====");
//        System.out.println("Nhập id sản phẩm muốn thêm mới");
//        int id = inputInt.nextInt();
        int id = Validator.inputId();
//        System.out.println("Nhập tên sản phẩm muốn thêm mới");
//        String name = inputString.nextLine();
        String name = Validator.inputName();
//        System.out.println("Nhập số lượng sản phẩm muốn thêm mới");
//        int quantity = inputInt.nextInt();
        int quantity = Validator.inputQuantity();
//        System.out.println("Nhập giá của sản phẩm muốn thêm mới : ");
//        double price = inputInt.nextDouble();
        double price = Validator.inputPrice();
//        System.out.println("Nhập loại của sản phẩm: ");
//        int type = inputInt.nextInt();
        int type = Validator.inputType();

        Product product = new Product(id,name,quantity,price,type);
        productManager.add(product);
        System.out.println("=======Đã thêm mới thành công =====");

    }

    public void showMenuEdit(){
        System.out.println("=======Đây là mục sửa dữ liệu sản phẩm =====");
        System.out.println("Nhập id sản phẩm muốn sửa");
        int id = Validator.inputId();
        if (productManager.findById(id)== -1){
            System.out.println("Không có sản phẩm này.");
        }
        else {
            System.out.println("Nhập tên sản phẩm muốn sửa đổi");
            String name = Validator.inputName();
            System.out.println("Nhập số lượng sản phẩm muốn sửa đổi");
            int quantity = Validator.inputQuantity();
            System.out.println("Nhập giá của sản phẩm muốn sửa đổi : ");
            double price = Validator.inputPrice();
            System.out.println("Nhập loại của sản phẩm: ");
            int type = Validator.inputType();
            Product product = new Product(id,name,quantity,price,type);
            productManager.edit(id,product);
            System.out.println("=======Đã sửa đổi thành công =====");

        }



    }
    public void showMenuDelete( ){
        System.out.println("=======Đây là mục xóa sản phẩm =====");
        System.out.println("Nhập id muốn xóa");
        int id = Validator.inputId();
        if (productManager.findById(id)== -1){
            System.out.println("Không có sản phẩm này.");
        }
        else
        {productManager.delete(id);}
        System.out.println("=======Đã xóa thành công =====");
    }
    public void showAll(){
        System.out.println("Danh sách sản phẩm  : ");
        List<Product> list= productManager.findAll();
        for (Product product:list) {
            System.out.println(product);
        }
    }
    public void showMenuShowNameProduct(){
        System.out.println("=======Đây là mục tìm kiếm sản phẩm  theo tên gần đúng  =====");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập ký tự muốn tìm kiếm");
        String charactor = scanner.nextLine();
        List<Product>list= productManager.findAll();
        List<Product>newList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().toLowerCase().contains(charactor.toLowerCase())){
                newList.add(list.get(i));
            }
        }
        System.out.println("Danh sách sản phẩm  có ký tự : " + charactor+ " là : ");
        for (Product product:newList) {
            System.out.println(product);
        }
    }
    public void showMenuProductSameType(){
        System.out.println("=======Đây là mục tìm kiếm và lấy ra sản phẩm cùng loại  =====");
        System.out.println("Nhập loại sản phẩm  bạn muốn tìm kiếm");
        int type = Validator.inputType();
        List<Product>list= productManager.findAll();
        List<Product>newList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getType()== type){
                newList.add(list.get(i));
            }
        }

        int index = productManager.findByType(type);
        if (index == -1){
            System.out.println("Không có sản phẩm này nhé.");
        }else {
            System.out.println("Những sản phẩm có loại"+type+ "là : ");
            for (Product product:newList) {
                System.out.println(product);
            }
        }
    }
    public void showMenuSearchById(){
        System.out.println("=======Đây là mục tìm kiếm sản phẩm  theo Id =====");
        System.out.println("Nhập id tên sản phẩm  bạn muốn tìm kiếm");
        int id = Validator.inputId();
        List<Product>list= productManager.findAll();
        int index = productManager.findById(id);
        if (index == -1){
            System.out.println("Không có sản phẩm này nhé.");
        } else {
            System.out.println(list.get(index));
        }

    }





}
