package bean.template;

import java.io.Serializable;

/**
 * Created by zsx on 2017/6/16.
 */
public class SearchDO<T> implements Serializable {
    private Integer page;
    private Integer size;
    private T searchData;
    private Order order;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public T getSearchData() {
        return searchData;
    }

    public void setSearchData(T searchData) {
        this.searchData = searchData;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public static class Order implements Serializable{
        private Order.Direction direction;
        private String property;
        public static enum Direction {
            ASC,
            DESC;
        }

        public Direction getDirection() {
            return direction;
        }

        public void setDirection(Direction direction) {
            this.direction = direction;
        }

        public String getProperty() {
            return property;
        }

        public void setProperty(String property) {
            this.property = property;
        }
    }


}
