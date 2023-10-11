package manager;

import model.Product;
import readAndWriteFile.ReadAndWriteFile;

import java.util.List;

public class ProductManager implements IManager<Product> {
    private List<Product>list;
    private ReadAndWriteFile readAndWriteFile = new ReadAndWriteFile();
    public ProductManager(){
        list = readAndWriteFile.readFile();
    }

    @Override
    public void add(Product product) {
        list.add(product);
        readAndWriteFile.writeFile(list);
    }

    @Override
    public boolean edit(int id, Product product) {
        int index = findById(id);
        if (index == -1){
            return false;
        }
        list.set(index,product);
        readAndWriteFile.writeFile(list);
        return true;
    }

    @Override
    public boolean delete(int id) {
        int index = findById(id);
        if (index == -1){
            return false;
        }
        list.remove(index);
        readAndWriteFile.writeFile(list);
        return true;

    }

    @Override
    public int findById(int id) {
        for (int i = 0; i < list.size(); i++) {
           if (list.get(i).getId()== id){
               return i;
           }
        }
        return -1;
    }

    @Override
    public int findByType(int type) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getType()== type){
                return i;
            }
        }
        return -1;
    }

    @Override
    public List<Product> findAll() {
        return list;
    }
}
