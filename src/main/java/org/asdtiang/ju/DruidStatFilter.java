package org.asdtiang.ju;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
  
import com.alibaba.druid.support.http.WebStatFilter;
  
/**
 * 
 * @author asdtiang asdtiangxia@163.com
 *
 * 2018年6月11日
 */
@WebFilter(filterName="druidWebStatFilter",urlPatterns="/*",
    initParams={
            @WebInitParam(name="exclusions",value="*.jpeg,*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*")//忽略资源
     }
)
public class DruidStatFilter extends WebStatFilter{
  
}