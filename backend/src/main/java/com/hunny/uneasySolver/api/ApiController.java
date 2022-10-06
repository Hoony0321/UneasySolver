package com.hunny.uneasySolver.api;

import com.hunny.uneasySolver.domain.Job;
import com.hunny.uneasySolver.domain.Target;
import com.hunny.uneasySolver.repository.JobRepository;
import com.hunny.uneasySolver.repository.TargetRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ApiController {

    private final TargetRepository targetRepository;
    private final JobRepository jobRepository;

    @Data
    public class targetDTO{
        private String name;

        public targetDTO(){}
        public targetDTO(Target target){
            this.name = target.getName();
        }
    }

    @GetMapping("/api/targets")
    public List<Target> targetList(){
        List<Target> targets = targetRepository.findAll();
        return targets;
    }

    @GetMapping("/api/jobs")
    public List<Job> jobList(){
        List<Job> jobs = jobRepository.findAll();
        return jobs;
    }
}
