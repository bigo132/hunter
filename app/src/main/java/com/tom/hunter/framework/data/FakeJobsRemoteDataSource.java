package com.tom.hunter.framework.data;

import android.support.annotation.NonNull;

import com.tom.hunter.framework.data.remote.IRemoteDataSource;
import com.tom.hunter.model.Address;
import com.tom.hunter.model.Company;
import com.tom.hunter.model.Job;
import com.tom.hunter.model.Photo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by txu1 on 9/5/2016.
 */
public class FakeJobsRemoteDataSource implements IRemoteDataSource {

    private static final List<Job> TASKS_SERVICE_DATA = new LinkedList<>();
    private static final Map<Integer, List<Job>> TASKS_ALL_DATA = new HashMap<>();
    private static FakeJobsRemoteDataSource INSTANCE;
    private static Address address;
    private static Company company;

    static {
        List<Photo> photos = new ArrayList<>();
        photos.add(new Photo("http://imgsrc.baidu.com/baike/pic/item/f703738da97739121bffd0c3f8198618377ae2b3.jpg", "办公环境"));
        photos.add(new Photo("http://pic92.nipic.com/file/20160324/7125099_143757269000_2.jpg", "前台"));
        address = new Address("咸阳市", "中国", "泾阳县", 34.535468, 108.866257, "陕西省", "街道", "X321", "710035");
        company = new Company(address, "索尼(中国)有限公司无锡软件中心创建于2010年11月，建立的目的是为了提高中国软件中心软件开发服务的竞争力。\n" +
                "无锡软件中心的业务是为索尼全球的各BU/事业部提供整体的软件开发/测试服务。从软件需求分析开始到后期软件维护，从软件开发的整个生命周期出发，提高软件质量，降低开发成本，为索尼产品的市场竞争力做出贡献。\n" +
                "无锡软件中心的策略是以索尼软件开发精英团队为依托，结合无锡本地软件外包的资源，一起打造能够承担起索尼产品整个生命周期的软件开发队伍，为索尼产品提供高质量低成本的软件产品。更进一步的目标是结合中国本地市场的需求，" +
                "从本地出发，精选出符合中国市场的产品需求，和各BU/事业部一起开发完成“激发并满足客户好奇心”的产品，为索尼产品扩大在中国市场的份额做出贡献。", 280, "http://imgsrc.baidu.com/baike/pic/item/f703738da97739121bffd0c3f8198618377ae2b3.jpg", "索尼(中国)有限公司无锡软件中心", 100, "01025638965", 1, "http://www.baidu.com", photos);
        for (int i = 0; i < 10; i++) {
            TASKS_SERVICE_DATA.add(new Job(i + "", company, 31, i +
                    "1.较强的C/C++编程背景和算法基础，熟悉面对对象编程，熟悉linux平台\n" +
                    "2.良好的英语沟通能力和团队合作精神\n" +
                    "3.符合以下条件之一优先考虑：\n" +
                    "4.熟悉GUI开发特别是QT架构的软件开发\n" +
                    "5.了解数据库知识\n" +
                    "6.了解XML及其解析器\n" +
                    "7.具备模拟电路设计的基础知识\n" +
                    "\n" +
                    "Position Description:\n" +
                    "1. Maintain and develop EMIR feature in Cadence fastSpice simulator.\n" +
                    "2. Be responsible for writing specifications, designing and implementing product enhancements and fixes.\n" +
                    "3. Needs to work with other global R&D teams.\n" +
                    "\n" +
                    "Position Requirements:\n" +
                    "1. Education requirement: Master or Ph.D. in EE/CS/related research area.\n" +
                    "2. Skilled in C/C++ programming, and familiar with development under Linux/Unix environment.\n" +
                    "3. Experience of analog circuit design is a strong plus.\n" +
                    "4. Good English communication skills, both verbally and in writing\n" +
                    "\n" +
                    "If you have interest, PLS send your update CV to job_china@cadence.com", "Senior Software Engineer", 0, 0, 20000, 10000));
        }
    }

    // Prevent direct instantiation.
    private FakeJobsRemoteDataSource() {
    }

    public static FakeJobsRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new FakeJobsRemoteDataSource();
        }
        return INSTANCE;
    }

    @Override
    public void getPromptJobs(@NonNull LoadJobsCallback callback) {
        callback.onJobsLoaded(TASKS_SERVICE_DATA.subList(0, 3));
    }

    @Override
    public void getAllJobs(@NonNull LoadAllJobsCallback callback) {
        TASKS_ALL_DATA.put(0, TASKS_SERVICE_DATA.subList(0, 3));
        TASKS_ALL_DATA.put(1, TASKS_SERVICE_DATA);
        callback.onJobsLoaded(TASKS_ALL_DATA);
    }

    @Override
    public void getDetailJob(@NonNull String jobId, @NonNull GetJobCallback callback) {
        int id = Integer.parseInt(jobId);
        callback.onJobLoaded(TASKS_SERVICE_DATA.get(id));
    }

    @Override
    public void getDetailCompany(@NonNull GetCompanyCallback callback) {
        callback.onCompanyLoaded(company);
    }

}
