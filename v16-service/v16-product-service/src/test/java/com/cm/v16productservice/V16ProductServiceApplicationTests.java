package com.cm.v16productservice;

import com.cm.v16.api.IProductService;
import com.cm.v16.api.IProductTypeService;
import com.cm.v16.entity.TProduct;
import com.cm.v16.entity.TProductType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class V16ProductServiceApplicationTests {

	@Autowired
	private IProductService productService;

	@Autowired
	private DataSource dataSource;

	@Autowired
	private IProductTypeService productTypeService;

	@Test
	public void contextLoads(){
		TProduct product=productService.selectByPrimaryKey(1L);
		System.out.println(product.getName()+"-->"+product.getPrice());
	}	

	@Test
	public void testConnection() throws SQLException{
		System.out.println(dataSource.getConnection());
	}

	@Test
	public void testList() {
		List<TProductType> list=productTypeService.list();
		for(TProductType tProductType : list){
			System.out.println(tProductType.getId()+"->"+tProductType.getName());
		}
	}
}
