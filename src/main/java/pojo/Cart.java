package pojo;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author liaoke
 * @create 2021-11-09-22:40
 */
public class Cart {


    /*
    key 为商品编号 value 为商品信息
     */
    private Map<Integer , CartItem> Items=new LinkedHashMap<>();

  /**
   * @description 添加商品项
   * @author HDLaZy
   * @updateTime 2021/11/9 22:56
   */
   public void addItem(CartItem cartItem){

       //查看购物车中是否有此商品
       //有该商品则累加数量 没有改商品则直接放到集合中
       CartItem Item = Items.get(cartItem.getId());

       if(Item==null){
           Items.put(cartItem.getId(),cartItem);
       }else{
           Item.setCount(Item.getCount()+1);
           Item.setTotalPrice(Item.getPrice().multiply(new BigDecimal(Item.getCount())));
       }
   }

   /**
    * @description 删除商品项
    * @author HDLaZy
    * @updateTime 2021/11/9 22:56
    */
   public void deleteItem(Integer id){

       Items.remove(id);

   }

    /**
     * @description 清空购物车
     * @author HDLaZy
     * @updateTime 2021/11/9 22:56
     */
    public void clean(){

        Items.clear();

    }

    /**
     * @description 修改商品数量
     * @author HDLaZy
     * @updateTime 2021/11/9 22:56
     */
    public void updateCount(Integer id,Integer count){

        CartItem cartItem = Items.get(id);

        if(cartItem!=null){
            cartItem.setCount(count);
            cartItem.setTotalPrice(cartItem.getPrice().multiply(new BigDecimal(cartItem.getCount())));
        }

    }

    public Integer getTotalCount() {

        Integer totalCount=0;

        for(Map.Entry<Integer,CartItem> entry:Items.entrySet()){
            totalCount  += entry.getValue().getCount();
        }

        return totalCount;
    }



    public BigDecimal getTotalPrice() {
      BigDecimal  totalPrice=new BigDecimal(0);

      for(Map.Entry<Integer,CartItem> entry:Items.entrySet()){
          totalPrice=totalPrice.add(entry.getValue().getTotalPrice());
      }

      return totalPrice;
    }



    public Map<Integer, CartItem> getItems() {
        return Items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        Items = items;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice()+
                ", Items=" + Items +
                '}';
    }
}
