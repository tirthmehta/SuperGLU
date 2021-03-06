package Util.Tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import Util.StorageToken;
import Util.tokenformat.JSONRWFormat;

public class JSONRWFormatTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testParse() {
		fail("Not yet implemented");
	}

	@Test
	public void testSerializeStorageToken() {
		Map<String, Object> data = new HashMap<>();
		data.put("str1", "value");
		data.put("int1", 10);
		data.put("float1", 3.14);
		data.put("bool1", true);
		data.put("nullVal", null);
		StorageToken token = new StorageToken(data, "id", "classID");
		
		String json = JSONRWFormat.serialize(token);
		
		Assert.assertEquals("{\"classID\":{\"float1\":3.14,\"nullVal\":null,\"classId\":\"classID\",\"int1\":10,\"str1\":\"value\",\"id\":\"id\",\"bool1\":true}}", json);
		
		System.out.println(json);
	}
	
	
	@Test
	public void testSerializeList()
	{
		Map<String, Object> data = new HashMap<>();
		List<String> stringList = new ArrayList<>();
		
		stringList.add("string1");
		stringList.add("string2");
		stringList.add("string3");
		data.put("stringList", stringList);
		
		
		
		StorageToken token = new StorageToken(data, "id", "classID");
		
		String json = JSONRWFormat.serialize(token);
		
		Assert.assertEquals("{\"classID\":{\"classId\":\"classID\",\"stringList\":{\"list\":[\"string1\",\"string2\",\"string3\"]},\"id\":\"id\"}}", json);
		
		System.out.println(json);
	}
	
	
	@Test
	public void testSerializeMap()
	{
		Map<String, Object> data = new HashMap<>();
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		map.put("key1", 13);
		map.put("key2", 32);
		map.put("key3", 1300);
		data.put("stringIntMap", map);
		
		
		
		StorageToken token = new StorageToken(data, "id", "classID");
		
		String json = JSONRWFormat.serialize(token);
		
		Assert.assertEquals("{\"classID\":{\"classId\":\"classID\",\"stringIntMap\":{\"map\":{\"key1\":13,\"key2\":32,\"key3\":1300}},\"id\":\"id\"}}", json);
		
		System.out.println(json);
	}
	
	
	@Test
	public void testSerializeNestedObjects()
	{
		Map<String, Object> data = new HashMap<>();
		data.put("str1", "value");
		data.put("int1", 10);
		data.put("float1", 3.14);
		data.put("bool1", true);
		StorageToken innerToken1 = new StorageToken(data, "id1", "classID1");
		
		Map<String, Object> data2 = new HashMap<>();
		data2.put("str1", "value2");
		data2.put("int1", 1113);
		data2.put("float1", 43212);
		data2.put("bool1", true);
		StorageToken innerToken2 = new StorageToken(data2, "id2", "classID2");
		
		List<StorageToken> innerTokens = new ArrayList<>();
		innerTokens.add(innerToken1);
		innerTokens.add(innerToken2);
		
		Map<String, Object> outerData = new HashMap<>();
		outerData.put("innerTokens", innerTokens);
		StorageToken outerToken = new StorageToken(outerData, "outerToken", "TestData");
		
		String json = JSONRWFormat.serialize(outerToken);

		Assert.assertEquals("{\"TestData\":{\"classId\":\"TestData\",\"innerTokens\":{\"list\":[{\"classID1\":{\"float1\":3.14,\"classId\":\"classID1\",\"int1\":10,\"str1\":\"value\",\"id\":\"id1\",\"bool1\":true}},{\"classID2\":{\"float1\":43212,\"classId\":\"classID2\",\"int1\":1113,\"str1\":\"value2\",\"id\":\"id2\",\"bool1\":true}}]},\"id\":\"outerToken\"}}", json);
		
		System.out.println(json);
		
		
		
		
	}

}
