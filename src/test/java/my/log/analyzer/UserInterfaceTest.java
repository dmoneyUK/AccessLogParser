package my.log.analyzer;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * A test class for {@link UserInterface}.
 * 
 * @author Jinge Dai
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class UserInterfaceTest {

	/** The {@link UserInterface} under test. */
	private UserInterface userInterface;

	@Mock
	private AccessLogParser mockAccessLogParser;

	/**
	 * Runs before each test.
	 */
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		this.userInterface = new UserInterface();
	}

	@Test
	public void testShowResult() {
		Map<Date, AccessRecord> recordMap = TestUtils
				.createSampleAccessRecordMap();
		Mockito.when(this.mockAccessLogParser.getRecordMap()).thenReturn(
				recordMap);
		List<Date> keys = new ArrayList<Date>();
		keys.add(TestUtils.SAMPLE_TIME_STAM[0]);
		keys.add(TestUtils.SAMPLE_TIME_STAM[1]);
		Mockito.when(this.mockAccessLogParser.sortMapKeys()).thenReturn(keys);
		this.userInterface.showResult(this.mockAccessLogParser);

		Mockito.verify(this.mockAccessLogParser).getRecordMap();
		Mockito.verify(this.mockAccessLogParser).sortMapKeys();

		TestUtils.verifyRecord(recordMap.get(keys.get(0)),
				TestUtils.SAMPLE_SUCCESS_NUM[0], TestUtils.SAMPLE_ERROR_NUM[0],
				TestUtils.SAMPLE_RESPONSE_TIME[0],
				TestUtils.SAMPLE_RETURN_SIZE[0]);
		TestUtils.verifyRecord(recordMap.get(keys.get(1)),
				TestUtils.SAMPLE_SUCCESS_NUM[1], TestUtils.SAMPLE_ERROR_NUM[1],
				TestUtils.SAMPLE_RESPONSE_TIME[1],
				TestUtils.SAMPLE_RETURN_SIZE[1]);

	}

	/**
	 * Tests {@link UserInterface#readUserInput()} with a valid input.
	 */
	@Test
	public void testReadUserInput(){
		ByteArrayInputStream in = new ByteArrayInputStream(TestUtils.SAMPLE_MESSAGE.getBytes());
		System.setIn(in);
		String actual = this.userInterface.readUserInput();
		Assert.assertEquals(TestUtils.SAMPLE_MESSAGE, actual);	
	}
	
	/**
	 * Tests {@link UserInterface#showMessage(String)}.
	 */
	@Test
	public void testShowMessage() {
		this.userInterface.showMessage(TestUtils.SAMPLE_MESSAGE);
	}

	/**
	 * Tests {@link UserInterface#showMessage(String)} with null for the
	 * parameter.
	 */
	@Test
	public void testShowMessage_NullForMessage() {
		this.userInterface.showMessage(null);
	}

}
