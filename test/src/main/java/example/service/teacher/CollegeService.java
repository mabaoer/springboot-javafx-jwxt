package example.service.teacher;

import example.dao.CollegeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CollegeService {
    final
    CollegeRepository collegeDao;
    @Autowired
    public CollegeService(CollegeRepository collegeDao) {
        this.collegeDao = collegeDao;
    }
    public String findCollegeNameByCollegeId(Integer collegeId) {
        return collegeDao.findCollegeNameByCollegeId(collegeId);
    }
}
