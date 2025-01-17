package application;
//key = job
//value1 = utilizatorul care trb angajat
//value2 = recruiter
//score = scorul recruiterului
public class Request<K, V> implements Comparable{
	private K key;
	private V value1, value2;
	private Double score;
	public Request(K key, V value1, V value2, Double score) {
		this.key = key;
		this.value1 = value1;
		this.value2 = value2;
		this.score = score;
	}
	public K getKey() {
		return key;
	}
	public V getValue1() {
		return value1;
	}
	public V getValue2() {
		return value2;
	}
	public Double getScore() {
		return score;
	}
	public String toString() {
		return "Key: " + key + " ; Value1: " + value1 + " ;Value2: "+ value2+" Score: "+score;
	}
	public int compareTo(Object request) {
		Request myRequest = (Request) request;
		if(score > myRequest.score)
			return -1;
		else if(score == myRequest.score)
			return 0;
		return 1;
	}
	
}
