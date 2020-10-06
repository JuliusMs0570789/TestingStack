package postfix;

import static org.junit.Assert.assertEquals;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class PostfixEvaluationTest {
	String comment, infix, postfix;
	double result;
	Postfix postfixEvaluator;

	public PostfixEvaluationTest(String comment, String infix, String postfix,
								 double result) {
		this.comment = comment;
		this.infix = infix;
		this.postfix = postfix;
		this.result = result;
	}

	@Parameters
	public static Collection<Object[]> data() {
		Collection<Object[]> data = TestCases.generalTestCases();
		data.addAll(TestCases.exponentialTestCases());
		data.addAll(TestCases.multiDigitTestCases());
		return data;
	}

	@Before
	public void createPostfix() {
		postfixEvaluator = new Postfix();
	}

	@Test
	public void testEvaluatePostfix() {
		assertEquals(comment + " (postfix was: " + postfix+")", result,
				postfixEvaluator.evaluate(postfix), 0.0);
	}

}