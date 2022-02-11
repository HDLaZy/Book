package pojo;

import java.util.List;

/**
 * @author liaoke
 * @create 2021-10-29-3:34
 */

public class Page<T> {

    public static final Integer PAGE_SIZE=4;

    //分页的请求地址
    private String url;

    private Integer pageNo;
    private Integer pageTotal;
    private Integer pageSize=PAGE_SIZE;
    private Integer pageTotalCount;
    private List<T> items;

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        if(pageNo>this.pageTotal){
            pageNo=this.pageTotal;
        }
        if(pageNo<1){
            pageNo=1;
        }
        this.pageNo = pageNo;
    }

    public Integer getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageTotalCount() {
        return pageTotalCount;
    }

    public void setPageTotalCount(Integer pageTotalCount) {
        this.pageTotalCount = pageTotalCount;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Page{" +
                "url='" + url + '\'' +
                ", pageNo=" + pageNo +
                ", pageTotal=" + pageTotal +
                ", pageSize=" + pageSize +
                ", pageTotalCount=" + pageTotalCount +
                ", items=" + items +
                '}';
    }
}
