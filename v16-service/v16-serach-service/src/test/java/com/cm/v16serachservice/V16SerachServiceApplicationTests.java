package com.cm.v16serachservice;

import com.cm.v16.api.ISerachApi;
import com.cm.v16.common.pojo.PageResultBean;
import com.cm.v16.common.pojo.ResultBean;
import com.cm.v16.entity.TProduct;
import com.cm.v16.mapper.TProductMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class V16SerachServiceApplicationTests {

	@Autowired
	private TProductMapper productMapper;

	@Autowired
	private ISerachApi serachApi;

	@Test
	public void testSerachApi() {
		ResultBean resultBean=serachApi.synAllData();
		System.out.println(resultBean.getData());
	}

	@Test
	public void testAddOrUpdate(){
		List<HashMap<String,Object>> arr=new ArrayList<>();
		for(int i=1;i<4;i++){
			HashMap<String,Object> hashMap=new HashMap<>();
			hashMap.put("id","8"+i);
			hashMap.put("product_name","华为meta"+i);
			arr.add(hashMap);
		}
		ResultBean resultBean=serachApi.addOrUpdateData(arr);
		System.out.println(resultBean.getData());

	}

	@Test
	public void testQueryData(){
		ResultBean resultBean=serachApi.queryData("华为");
		List<TProduct> data=(List<TProduct>) resultBean.getData();
		for(TProduct datum : data){
			System.out.println(datum.getId()+"->"+datum.getName());
		}
	}

	@Test
	public  void testDeleteByIdData(){
		ResultBean resultBean=serachApi.deleteByIdData(1L);
		System.out.println(resultBean.getData());
	}

	@Test
	public void testSplitPage(){
		/*  //结果集
    private List<T> list;*/
		PageResultBean pageResultBean=serachApi.getList("华为", 1, 5);
//		System.out.println(productPageResultBean2.getList().size()+"-->");
		System.out.println(pageResultBean.getList().size());
	}

}
