import org.junit.runner.RunWith;

/**
 * IntelliJ IDEA
 *
 * @author gaoshang
 * date: 2020/11/7 下午3:23
 */
@RunWith(SpringRun)
@Rollc
public class BaseApplicationTests {

    /**
     * 测试执行开始时间
     */
    private long executeStart = 0;

    @Before
    public void before() {
        System.out.println("测试执行开始");
        executeStart = System.currentTimeMillis();
    }

    @Test
    public void contextLoads() {
        try {
            Thread.sleep(200L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @After
    public void after() {
        System.out.println("测试执行完成，执行时间：" + (System.currentTimeMillis() - executeStart) / 1000.0 + "s");
    }

}
