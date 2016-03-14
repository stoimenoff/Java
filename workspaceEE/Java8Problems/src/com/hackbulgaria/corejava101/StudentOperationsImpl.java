package com.hackbulgaria.corejava101;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.hackbulgaria.corejava101.data.Gender;
import com.hackbulgaria.corejava101.data.Student;

public class StudentOperationsImpl implements StudentOperations {

	private List<Student> students;
	
	public StudentOperationsImpl(List<Student> students) {
		this.students = students;
	}
	
	@Override
	public double getAverageMark() {
		return students.parallelStream()
				.mapToDouble(Student::getGrade)
				.sum() / students.size();
	}

	@Override
	public List<Student> getAllPassing() {
		return students.parallelStream()
				.filter(s -> s.getGrade() >= 3.0f)
				.collect(Collectors.toList());
	}

	@Override
	public List<Student> getAllFailing() {
		return students.parallelStream()
				.filter(s -> s.getGrade() < 3.0f)
				.collect(Collectors.toList());
	}

	@Override
	public Map<Boolean, List<Student>> splitStudentsByMarks(float splitMark) {
		return students.parallelStream()
				.collect(Collectors
							.groupingBy(
									s -> s.getGrade() >= splitMark
							));
	}

	@Override
	public List<Student> orderByMarkDescending() {
		return students.parallelStream()
				.sorted((s1, s2) -> new Double(s2.getGrade()).compareTo(new Double(s1.getGrade())))
				.collect(Collectors.toList());
	}

	@Override
	public List<Student> orderByMarkAscending() {
		return students.parallelStream()
				.sorted((s1, s2) -> new Double(s1.getGrade()).compareTo(new Double(s2.getGrade())))
				.collect(Collectors.toList());
	}

	@Override
	public List<Student> getStudentsWithLowestMarks() {
		double min = students.parallelStream()
				.mapToDouble(Student::getGrade)
				.min()
				.getAsDouble();
		return students.parallelStream()
				.filter(s -> s.getGrade() == min)
				.collect(Collectors.toList());
	}

	@Override
	public List<Student> getStudentsWithHighestMarks() {
		double max = students.parallelStream()
								.mapToDouble(Student::getGrade)
								.max()
								.getAsDouble();
		return students.parallelStream()
						.filter(s -> s.getGrade() == max)
						.collect(Collectors.toList());
	}

	@Override
	public Map<Integer, List<Double>> getMarksDistributionByAge() {
		return students.parallelStream()
				.collect(Collectors
							.groupingBy(
									Student::getAge,
									Collectors.mapping(
						                    Student::getGrade,
						                    Collectors.toList())
							));
	}

	@Override
	public Map<Gender, Double> getAverageMarkByGender() {
		return students.parallelStream()
				.collect(Collectors
							.groupingBy(
									Student::getGender,
									Collectors.averagingDouble(Student::getGrade)
							));
	}

	@Override
	public Map<Double, Integer> getMarksDistribution() {
		return students.parallelStream()
				.collect(Collectors
							.groupingBy(
									Student::getGrade,
									Collectors.reducing(0, e -> 1, Integer::sum)
							));
	}

	@Override
	public String getEmailToHeader() {
		return students.parallelStream()
				.map(Student::getEmail)
				.collect(Collectors.joining(","));
	}

	@Override
	public Map<Gender, Map<Integer, List<Student>>> splitStudentMarksByGenderAndThenByAge() {
		// TODO Auto-generated method stub
		return students.parallelStream()
					.collect(Collectors
							.groupingBy(
									Student::getGender,
									Collectors.groupingBy(Student::getAge)
									)
							);
	}

}
