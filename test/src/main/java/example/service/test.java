package example.service;

import example.dao.StudentRepository;
import example.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author mbw
 * @date 2020/5/9 21:45
 */
@Service
public class test {
    @Autowired
    public StudentRepository studentRepository;
    public List<Student> test(){
        System.out.println("张宏伟牛逼");
        return studentRepository.findAll();
    }
}
