package application;

import java.time.LocalDate;
import java.util.*;
abstract public class Consumer {
	List<Consumer> linkedConsumers;
	Resume consumerResume;
	
	public Consumer() {
		linkedConsumers = null;
		consumerResume = null;
	}
	public Consumer(List<Consumer> linkedConsumers,Resume consumerResume) {
		this.linkedConsumers = linkedConsumers;
		this.consumerResume = consumerResume;
	}
	public void add (Education education) {
		consumerResume.education.add(education);
	}
	public void add (Experience experience) {
		consumerResume.experience.add(experience);
	}
	public void add (Consumer consumer) {
		if(linkedConsumers == null)
			linkedConsumers = new ArrayList<Consumer>();
		linkedConsumers.add(consumer);
		if(consumer.linkedConsumers == null)
			consumer.linkedConsumers = new ArrayList<Consumer>();
		consumer.linkedConsumers.add(this);
	}
	public int getDegreeInFriendship(Consumer consumer) {
		HashMap<Consumer,Integer> visited = new HashMap<Consumer,Integer>();
        LinkedList<Consumer> queue = new LinkedList<Consumer>();
        
        int degree = 0 ;
        int level_size;
        visited.put(this, 1);
        queue.add(this);
        while(queue.size() != 0) {
        	level_size = queue.size();
        	while(level_size != 0) {
            	Consumer next = queue.poll();
            	if(next == consumer)
            		return degree;
	        	if(next.linkedConsumers != null) {
	        		Iterator<Consumer> iter = next.linkedConsumers.listIterator();
	        		while(iter.hasNext()) {
	        			Consumer n = iter.next();
	        			if(visited.get(n) == null) {
	        				visited.put(n, 1);
	        				queue.add(n);
	        			}
	        		}
	        	}
	        	level_size--;
        	}
        	degree++;
        }
		return -1;
	}
	public void remove(Consumer consumer) {
		linkedConsumers.remove(consumer);
	}
	public Integer getGraduationYear() {
		Education university = consumerResume.education.first();
		if(consumerResume.education.first().finishTime == null)
			return LocalDate.now().getYear() + 1;
		return university.finishTime.getYear();
	}
	
	public Double meanGPA() {
		double sum = 0 ;
		for(Education ed: this.consumerResume.education){
			sum += ed.meanGPA;
		}
		return sum/this.consumerResume.education.size();
	}
}
