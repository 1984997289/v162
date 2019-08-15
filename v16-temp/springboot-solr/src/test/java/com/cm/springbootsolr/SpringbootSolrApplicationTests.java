package com.cm.springbootsolr;

import com.cm.v16.api.IProductService;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootSolrApplicationTests {

	/*@Autowired
	private IProductService productService;*/

	@Autowired
	private SolrClient client;

	@Test
	public void addOrUpdate() throws IOException, SolrServerException{
		SolrInputDocument document=new SolrInputDocument();
		document.setField("id","01");
		document.setField("product_name","华为");
		document.setField("product_price","3999");
		document.setField("product_sale_point","通话时长");
		document.setField("product_images","aaa");
		client.add(document);
		client.commit();
	}

	@Test
	public void query() throws IOException, SolrServerException{
		SolrQuery query=new SolrQuery();
		query.setQuery("product_price:华为");

		QueryResponse response=client.query(query);

		SolrDocumentList results=response.getResults();

		long numFound=results.getNumFound();
		System.out.println("numFound："+numFound);
		for(SolrDocument result : results){
			System.out.println("product_name::"+result.get("product_name"));
			System.out.println("product_price::"+result.get("product_price"));
		}

	}

	@Test
	public void delete() throws IOException, SolrServerException{
		client.deleteByQuery("product_name:华为");
		client.commit();
	}
}
