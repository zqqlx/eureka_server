//package net.xdclass.eureka_server.service;
//
//import com.google.common.hash.BloomFilter;
//import com.google.common.hash.Funnel;
//import com.google.common.hash.Funnels;
//import net.xdclass.eureka_server.domain.entity.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.util.CollectionUtils;
//
//import javax.annotation.PostConstruct;
//import java.util.List;
//
//@Service
//public class BloomFilterService {
//
//    @Autowired
//    private BloomFilter<Integer> bf;
//
//    @Autowired
//    private IUserService userService;
//
//    @PostConstruct
//    void initBloomFilter() {
//        List<User> users = userService.selectList(null);
//        if (CollectionUtils.isEmpty(users)) {
//            return;
//        }
//        bf = BloomFilter.create(Funnels.integerFunnel(),users.size());
//        for ( User user : users) {
//            bf.put(user.getId());
//        }
//    }
//
//
//    public boolean keyExists(Integer id) {
//       if (bf.mightContain(id)) {
//           return true;
//       }
//       return false;
//    }
//}
