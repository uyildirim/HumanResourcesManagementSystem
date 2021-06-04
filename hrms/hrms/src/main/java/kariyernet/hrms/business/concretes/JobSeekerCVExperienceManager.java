package kariyernet.hrms.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import kariyernet.hrms.core.utilities.result.*;
import kariyernet.hrms.business.Messages;
import kariyernet.hrms.business.abstracts.JobSeekerCVExperienceService;
import kariyernet.hrms.core.utilities.result.DataResult;
import kariyernet.hrms.core.utilities.result.Result;
import kariyernet.hrms.dataAccess.abstracts.JobSeekerCVExperienceDao;
import kariyernet.hrms.entities.concretes.JobSeekerCVExperience;
import java.util.*;
@Service
public class JobSeekerCVExperienceManager implements JobSeekerCVExperienceService {
	private JobSeekerCVExperienceDao jobSeekerCVExperienceDao;

	@Autowired
	public JobSeekerCVExperienceManager(final JobSeekerCVExperienceDao jobSeekerCVExperienceDao) {
		this.jobSeekerCVExperienceDao = jobSeekerCVExperienceDao;
	}

	@Override
	public Result add(final JobSeekerCVExperience jobSeekerCVExperience) {
		jobSeekerCVExperienceDao.save(jobSeekerCVExperience);

		return new SuccessResult(Messages.jobSeekerCVExperienceAdded);
	}

	@Override
	public Result delete(final JobSeekerCVExperience jobSeekerCVExperience) {
		jobSeekerCVExperienceDao.delete(jobSeekerCVExperience);

		return new SuccessResult(Messages.jobSeekerCVExperienceDeleted);
	}

	@Override
	public DataResult<List<JobSeekerCVExperience>> getAll() {
		final List<JobSeekerCVExperience> jobSeekerCVExperiences = jobSeekerCVExperienceDao.findAll();

		return new SuccessDataResult<List<JobSeekerCVExperience>>(jobSeekerCVExperiences);
	}

	@Override
	public DataResult<List<JobSeekerCVExperience>> getAllByJobSeekerCV_Id(final int jobSeekerCVId) {
		final List<JobSeekerCVExperience> jobSeekerCVExperiences = jobSeekerCVExperienceDao
				.findAllByJobSeekerCV_Id(jobSeekerCVId);

		return new SuccessDataResult<List<JobSeekerCVExperience>>(jobSeekerCVExperiences);
	}

	@Override
	public DataResult<List<JobSeekerCVExperience>> getAllByJobSeekerCV_IdOrderByQuitDate(final int jobSeekerCVId,
			final Direction direction) {
		final List<JobSeekerCVExperience> jobSeekerEducations = direction.isAscending()
				? jobSeekerCVExperienceDao.findAllByJobSeekerCV_IdOrderByQuitDate(jobSeekerCVId)
				: jobSeekerCVExperienceDao.findAllByJobSeekerCV_IdOrderByQuitDateDesc(jobSeekerCVId);

		return new SuccessDataResult<List<JobSeekerCVExperience>>(jobSeekerEducations);
	}

	@Override
	public DataResult<JobSeekerCVExperience> getById(final Integer id) {
		final Optional<JobSeekerCVExperience> jobSeekerCVExperience = jobSeekerCVExperienceDao.findById(id);

		if (jobSeekerCVExperience.isPresent())
			return new ErrorDataResult<JobSeekerCVExperience>(Messages.jobSeekerCVExperienceNotFound);

		return new SuccessDataResult<JobSeekerCVExperience>(jobSeekerCVExperience.get());
	}

	@Override
	public Result update(final JobSeekerCVExperience jobSeekerCVExperience) {
		jobSeekerCVExperienceDao.save(jobSeekerCVExperience);

		return new SuccessResult(Messages.jobSeekerCVExperienceUpdated);
	}

}
