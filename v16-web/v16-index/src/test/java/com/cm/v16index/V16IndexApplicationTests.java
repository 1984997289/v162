package com.cm.v16index;

import com.cm.v16.api.IProductTypeService;
import com.cm.v16.entity.TProductType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class V16IndexApplicationTests {

	@Autowired
	private IProductTypeService productTypeService;

	@Test
	public void contextLoads() {
		List<TProductType> list=productTypeService.list();
		for(TProductType tProductType : list){
			System.out.println(tProductType.getId()+"->"+tProductType.getName());
		}
	}

}
