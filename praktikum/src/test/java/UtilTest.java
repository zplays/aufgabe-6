import static org.junit.Assert.*;


import org.junit.Test;

import junit.framework.Assert;

public class UtilTest {

	@Test
	public void test() {
		Assert.assertEquals(true, Util.istErstesHalbjahr(6));
		Assert.assertEquals(false, Util.istErstesHalbjahr(8));
	}

}
