package com.crimsonlogic.onlinejobportal.serviceimpl;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crimsonlogic.onlinejobportal.dto.CandidateDTO;
import com.crimsonlogic.onlinejobportal.dto.JobDTO;
import com.crimsonlogic.onlinejobportal.dto.RecruiterDTO;
import com.crimsonlogic.onlinejobportal.entity.Candidate;
import com.crimsonlogic.onlinejobportal.entity.Job;
import com.crimsonlogic.onlinejobportal.entity.JobLocation;
import com.crimsonlogic.onlinejobportal.entity.JobSkill;
import com.crimsonlogic.onlinejobportal.entity.Location;
import com.crimsonlogic.onlinejobportal.entity.Recruiter;
import com.crimsonlogic.onlinejobportal.entity.Skill;
import com.crimsonlogic.onlinejobportal.repository.CandidateRepository;
import com.crimsonlogic.onlinejobportal.repository.JobRepository;
import com.crimsonlogic.onlinejobportal.repository.LocationRepository;
import com.crimsonlogic.onlinejobportal.repository.RecruiterRepository;
import com.crimsonlogic.onlinejobportal.repository.SkillRepository;
import com.crimsonlogic.onlinejobportal.service.JobService;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private JobRepository jobRepository;
    
    @Autowired
    private SkillRepository skillRepository;
    
    @Autowired
    private LocationRepository locationRepository;
    
    @Autowired
    private RecruiterRepository recruiterRepository;
    
    @Autowired
    private CandidateRepository candidateRepository;
    
    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional
    public void saveJob(JobDTO jobDTO) {
        Job job = new Job();
        job.setJobTitle(jobDTO.getJobTitle());
        job.setEmploymentType(jobDTO.getEmploymentType());
        job.setDepartment(jobDTO.getDepartment());
        job.setWorkMode(jobDTO.getWorkMode());
        job.setMinExperience(jobDTO.getMinExperience());
        job.setMaxExperience(jobDTO.getMaxExperience());
        job.setMinSalary(jobDTO.getMinSalary());
        job.setMaxSalary(jobDTO.getMaxSalary());
        job.setJobDescription(jobDTO.getJobDescription());
        job.setVacancies(jobDTO.getVacancies());
        
        job.setQualification(jobDTO.getQualificationName());
        job.setCourse(jobDTO.getCourseName());
        job.setSpecialization(jobDTO.getSpecializationName());
        
        Recruiter recruiter = recruiterRepository.findByUserEmail(jobDTO.getRecruiterEmail());
        job.setRecruiter(recruiter);
        
        List<JobSkill> jobSkills = jobDTO.getKeySkillsIds().stream().map(skillId -> {
            Skill skill = skillRepository.findBySkillId(skillId)
                    .orElseThrow(() -> new RuntimeException("Skill not found with ID: " + skillId));
            JobSkill jobSkill = new JobSkill();
            jobSkill.setSkill(skill);
            jobSkill.setJob(job);
            return jobSkill;
        }).collect(Collectors.toList());
        job.setKeySkills(jobSkills);

        List<JobLocation> jobLocations = jobDTO.getJobLocationIds().stream().map(locationId -> {
            Location location = locationRepository.findByLocationId(locationId)
                    .orElseThrow(() -> new RuntimeException("Location not found with ID: " + locationId));
            JobLocation jobLocation = new JobLocation();
            jobLocation.setLocation(location);
            jobLocation.setJob(job);
            return jobLocation;
        }).collect(Collectors.toList());
        job.setJobLocations(jobLocations);

        jobRepository.save(job);
    }
    
    @Override
    @Transactional
    public List<JobDTO> getJobsByRecruiter(String recruiterEmail) {
        // Step 1: Find recruiter by email
        Recruiter recruiter = recruiterRepository.findByUserEmail(recruiterEmail);

        // Step 2: Find jobs associated with the recruiter
        List<Job> jobs = jobRepository.findByRecruiter(recruiter);

        // Step 3: Manually map the Recruiter entity to RecruiterDTO
        RecruiterDTO recruiterDTO = new RecruiterDTO();
        recruiterDTO.setFullName(recruiter.getFullName());
        recruiterDTO.setOfficialEmail(recruiter.getOfficialEmail());
        recruiterDTO.setCompanyName(recruiter.getCompanyName());
        recruiterDTO.setEmployeeRange(recruiter.getEmployeeRange());
        recruiterDTO.setDesignation(recruiter.getDesignation());
        recruiterDTO.setCompanyLocation(recruiter.getCompanyLocation());
        recruiterDTO.setCompanyAddress(recruiter.getCompanyAddress());
        recruiterDTO.setAboutCompany(recruiter.getAboutCompany());
        recruiterDTO.setIndustry(recruiter.getIndustry().getIndustryName()); // Map industry name
        recruiterDTO.setCompanyLogoUrl(recruiter.getCompanyLogoUrl());

        // Step 4: Manually map Job entities to JobDTOs
        return jobs.stream()
            .map(job -> {
                JobDTO jobDTO = new JobDTO();
                jobDTO.setJobTitle(job.getJobTitle());
                jobDTO.setEmploymentType(job.getEmploymentType());
                jobDTO.setDepartment(job.getDepartment());
                jobDTO.setWorkMode(job.getWorkMode());
                jobDTO.setMinExperience(job.getMinExperience());
                jobDTO.setMaxExperience(job.getMaxExperience());
                jobDTO.setMinSalary(job.getMinSalary());
                jobDTO.setMaxSalary(job.getMaxSalary());
                jobDTO.setJobDescription(job.getJobDescription());
                jobDTO.setVacancies(job.getVacancies());
                jobDTO.setRecruiterEmail(recruiter.getOfficialEmail());
                
                // Map core competency fields (qualification, course, specialization)
                jobDTO.setQualificationName(job.getQualification());
                jobDTO.setCourseName(job.getCourse());
                jobDTO.setSpecializationName(job.getSpecialization());
                
                jobDTO.setJobId(job.getJobId());

                // Map job locations (from JobLocation entities)
                List<String> locationNames = job.getJobLocations().stream()
                    .map(jobLocation -> jobLocation.getLocation().getLocationName())
                    .collect(Collectors.toList());
                jobDTO.setJobLocationIds(locationNames); // Assuming you're using location names here

                // Map key skills (from JobSkill entities)
                List<String> skillNames = job.getKeySkills().stream()
                    .map(jobSkill -> jobSkill.getSkill().getSkillName())
                    .collect(Collectors.toList());
                jobDTO.setKeySkillsIds(skillNames); // Assuming you're using skill names here

                // Assign the mapped recruiterDTO to the job
                jobDTO.setRecruiter(recruiterDTO);

                return jobDTO;
            })
            .collect(Collectors.toList());
    }

    
    @Override
    @Transactional
    public List<JobDTO> getAllJobs() {
        List<Job> jobs = jobRepository.findAll(); // Fetch all jobs

        // Map Job entities to JobDTOs
        return jobs.stream().map(job -> {
            JobDTO jobDTO = new JobDTO();
            jobDTO.setJobTitle(job.getJobTitle());
            jobDTO.setEmploymentType(job.getEmploymentType());
            jobDTO.setMinExperience(job.getMinExperience());
            jobDTO.setMaxExperience(job.getMaxExperience());
            jobDTO.setMinSalary(job.getMinSalary());
            jobDTO.setMaxSalary(job.getMaxSalary());
            jobDTO.setJobDescription(job.getJobDescription());
            jobDTO.setVacancies(job.getVacancies());

            // Map core competency fields (qualification, course, specialization)
            jobDTO.setQualificationName(job.getQualification());
            jobDTO.setCourseName(job.getCourse());
            jobDTO.setSpecializationName(job.getSpecialization());
            
            jobDTO.setJobId(job.getJobId());

            // Map job locations
            List<String> locations = job.getJobLocations().stream()
                    .map(jobLocation -> jobLocation.getLocation().getLocationName())
                    .collect(Collectors.toList());
            jobDTO.setJobLocationIds(locations);

            // Map key skills
            List<String> skills = job.getKeySkills().stream()
                    .map(jobSkill -> jobSkill.getSkill().getSkillName())
                    .collect(Collectors.toList());
            jobDTO.setKeySkillsIds(skills);

            // Safely handle recruiter mapping (null check)
            if (job.getRecruiter() != null) {
                RecruiterDTO recruiterDTO = new RecruiterDTO();
                recruiterDTO.setFullName(job.getRecruiter().getFullName());
                recruiterDTO.setCompanyName(job.getRecruiter().getCompanyName());
                recruiterDTO.setCompanyLocation(job.getRecruiter().getCompanyLocation());
                recruiterDTO.setCompanyLogoUrl(job.getRecruiter().getCompanyLogoUrl());
                recruiterDTO.setIndustry(job.getRecruiter().getIndustry().getIndustryName());
                jobDTO.setRecruiter(recruiterDTO);
            } else {
                // You can choose to set a default value or leave recruiter null in JobDTO
                jobDTO.setRecruiter(null); 
            }

            return jobDTO;
        }).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public JobDTO getJobById(String jobId) {
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found with ID: " + jobId));

        JobDTO jobDTO = new JobDTO();
        jobDTO.setJobTitle(job.getJobTitle());
        jobDTO.setEmploymentType(job.getEmploymentType());
        jobDTO.setMinExperience(job.getMinExperience());
        jobDTO.setMaxExperience(job.getMaxExperience());
        jobDTO.setMinSalary(job.getMinSalary());
        jobDTO.setMaxSalary(job.getMaxSalary());
        jobDTO.setJobDescription(job.getJobDescription());
        jobDTO.setVacancies(job.getVacancies());
        jobDTO.setQualificationName(job.getQualification());
        jobDTO.setCourseName(job.getCourse());
        jobDTO.setSpecializationName(job.getSpecialization());

        // Map job locations
        List<String> locations = job.getJobLocations().stream()
                .map(jobLocation -> jobLocation.getLocation().getLocationName())
                .collect(Collectors.toList());
        jobDTO.setJobLocationIds(locations);

        // Map key skills
        List<String> skills = job.getKeySkills().stream()
                .map(jobSkill -> jobSkill.getSkill().getSkillName())
                .collect(Collectors.toList());
        jobDTO.setKeySkillsIds(skills);

        // Recruiter details
        if (job.getRecruiter() != null) {
            RecruiterDTO recruiterDTO = new RecruiterDTO();
            recruiterDTO.setFullName(job.getRecruiter().getFullName());
            recruiterDTO.setCompanyName(job.getRecruiter().getCompanyName());
            recruiterDTO.setCompanyLocation(job.getRecruiter().getCompanyLocation());
            recruiterDTO.setCompanyLogoUrl(job.getRecruiter().getCompanyLogoUrl());
            recruiterDTO.setIndustry(job.getRecruiter().getIndustry().getIndustryName());
            jobDTO.setRecruiter(recruiterDTO);
        }

        return jobDTO;
    }

    @Override
    public List<CandidateDTO> getCandidatesByJobId(String jobId) {
        List<Candidate> candidates = candidateRepository.findCandidatesByJobId(jobId);
        return candidates.stream()
                .map(candidate -> modelMapper.map(candidate, CandidateDTO.class))
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional
    public List<JobDTO> getJobsByLocation(String location) {
        List<Job> jobs = jobRepository.findByLocation(location);

        // Map Job entities to JobDTOs
        return jobs.stream().map(job -> {
            JobDTO jobDTO = new JobDTO();
            jobDTO.setJobTitle(job.getJobTitle());
            jobDTO.setEmploymentType(job.getEmploymentType());
            jobDTO.setMinExperience(job.getMinExperience());
            jobDTO.setMaxExperience(job.getMaxExperience());
            jobDTO.setMinSalary(job.getMinSalary());
            jobDTO.setMaxSalary(job.getMaxSalary());
            jobDTO.setJobDescription(job.getJobDescription());
            jobDTO.setVacancies(job.getVacancies());

            // Map core competency fields (qualification, course, specialization)
            jobDTO.setQualificationName(job.getQualification());
            jobDTO.setCourseName(job.getCourse());
            jobDTO.setSpecializationName(job.getSpecialization());

            jobDTO.setJobId(job.getJobId());

            // Map job locations
            List<String> locations = job.getJobLocations().stream()
                    .map(jobLocation -> jobLocation.getLocation().getLocationName())
                    .collect(Collectors.toList());
            jobDTO.setJobLocationIds(locations);

            // Map key skills
            List<String> skills = job.getKeySkills().stream()
                    .map(jobSkill -> jobSkill.getSkill().getSkillName())
                    .collect(Collectors.toList());
            jobDTO.setKeySkillsIds(skills);

            // Recruiter details
            if (job.getRecruiter() != null) {
                RecruiterDTO recruiterDTO = new RecruiterDTO();
                recruiterDTO.setFullName(job.getRecruiter().getFullName());
                recruiterDTO.setCompanyName(job.getRecruiter().getCompanyName());
                recruiterDTO.setCompanyLocation(job.getRecruiter().getCompanyLocation());
                recruiterDTO.setCompanyLogoUrl(job.getRecruiter().getCompanyLogoUrl());
                recruiterDTO.setIndustry(job.getRecruiter().getIndustry().getIndustryName());
                jobDTO.setRecruiter(recruiterDTO);
            }

            return jobDTO;
        }).collect(Collectors.toList());
    }
    
    @Override
    @Transactional
    public List<JobDTO> getJobsByTitle(String title) {
        List<Job> jobs = jobRepository.findByJobTitleContainingIgnoreCase(title); // Query to find jobs by title

        // Map Job entities to JobDTOs (similar to how it was done for the getAllJobs method)
        return jobs.stream().map(job -> {
            JobDTO jobDTO = new JobDTO();
            jobDTO.setJobTitle(job.getJobTitle());
            jobDTO.setEmploymentType(job.getEmploymentType());
            jobDTO.setMinExperience(job.getMinExperience());
            jobDTO.setMaxExperience(job.getMaxExperience());
            jobDTO.setMinSalary(job.getMinSalary());
            jobDTO.setMaxSalary(job.getMaxSalary());
            jobDTO.setJobDescription(job.getJobDescription());
            jobDTO.setVacancies(job.getVacancies());

            // Map job locations
            List<String> locations = job.getJobLocations().stream()
                .map(jobLocation -> jobLocation.getLocation().getLocationName())
                .collect(Collectors.toList());
            jobDTO.setJobLocationIds(locations);

            // Map key skills
            List<String> skills = job.getKeySkills().stream()
                .map(jobSkill -> jobSkill.getSkill().getSkillName())
                .collect(Collectors.toList());
            jobDTO.setKeySkillsIds(skills);

            // Map recruiter details if available
            if (job.getRecruiter() != null) {
                RecruiterDTO recruiterDTO = new RecruiterDTO();
                recruiterDTO.setFullName(job.getRecruiter().getFullName());
                recruiterDTO.setCompanyName(job.getRecruiter().getCompanyName());
                recruiterDTO.setCompanyLocation(job.getRecruiter().getCompanyLocation());
                recruiterDTO.setCompanyLogoUrl(job.getRecruiter().getCompanyLogoUrl());
                jobDTO.setRecruiter(recruiterDTO);
            }

            return jobDTO;
        }).collect(Collectors.toList());
    }
    
    @Override
    @Transactional
    public List<JobDTO> getJobsByCompanies(List<String> companies) {
        List<Job> jobs = jobRepository.findByCompanyNamesIn(companies); // Assuming you have this method in your repository

        return jobs.stream()
            .map(this::convertJobToDTO)
            .collect(Collectors.toList());
    }


    @Override
    public List<String> getAllCompanies() {
        return jobRepository.findAllCompanies();
    }
    
    @Override
    @Transactional
    public List<JobDTO> getRecommendedJobsForCandidate(String candidateId) {
        // Step 1: Fetch candidate details
        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new RuntimeException("Candidate not found"));

        // Step 2: Fetch all jobs
        List<Job> jobs = jobRepository.findAll();

        // Step 3: Filter jobs based on candidate's profile
        return jobs.stream().filter(job -> {
            // Compare experience
            if (candidate.getWorkExperienceYears() < job.getMinExperience() || candidate.getWorkExperienceYears() > job.getMaxExperience()) {
                return false;
            }

            // Compare qualification
            if (!candidate.getHighestQualification().equalsIgnoreCase(job.getQualification())) {
                return false;
            }

            // Compare course
            if (!candidate.getCourse().equalsIgnoreCase(job.getCourse())) {
                return false;
            }

            // Compare specialization
            if (!candidate.getSpecialization().equalsIgnoreCase(job.getSpecialization())) {
                return false;
            }

            // Compare skills (Check if candidate has at least one of the required skills)
            Set<String> candidateSkills = candidate.getKeySkills().stream()
            	    .map(candidateSkill -> candidateSkill.getSkill().getSkillName()) // Access the skill name through the intermediary
            	    .collect(Collectors.toSet());

            Set<String> jobSkills = job.getKeySkills().stream().map(skill -> skill.getSkill().getSkillName()).collect(Collectors.toSet());
            if (Collections.disjoint(candidateSkills, jobSkills)) {
                return false; // No matching skills
            }

            return true; // Candidate matches the job
        }).map(this::convertJobToDTO).collect(Collectors.toList());
    }

    private JobDTO convertJobToDTO(Job job) {
        // Using ModelMapper to map job entity to JobDTO
        JobDTO jobDTO = modelMapper.map(job, JobDTO.class);

        // For any custom mapping, such as nested properties, you can add them here manually
        // e.g., map recruiter information separately or any nested objects not mapped automatically
        if (job.getRecruiter() != null) {
            RecruiterDTO recruiterDTO = modelMapper.map(job.getRecruiter(), RecruiterDTO.class);
            jobDTO.setRecruiter(recruiterDTO);
        }

        // If there are collections like skills or locations that need special handling:
        List<String> locationNames = job.getJobLocations().stream()
            .map(jobLocation -> jobLocation.getLocation().getLocationName())
            .collect(Collectors.toList());
        jobDTO.setJobLocationIds(locationNames);

        List<String> skillNames = job.getKeySkills().stream()
            .map(jobSkill -> jobSkill.getSkill().getSkillName())
            .collect(Collectors.toList());
        jobDTO.setKeySkillsIds(skillNames);

        return jobDTO;
    }

}
