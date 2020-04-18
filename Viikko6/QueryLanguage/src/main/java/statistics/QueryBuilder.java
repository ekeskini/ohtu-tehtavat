package statistics;


import statistics.matcher.*;

public class QueryBuilder {
	Matcher matcher;
	
	public QueryBuilder() {
		this.matcher = new All();
	}
	public Matcher build() {
		return matcher;
	}
	public QueryBuilder playsIn(String team) {
		this.matcher = new And(this.matcher, new PlaysIn(team));
		return this;
	}
	public QueryBuilder hasAtLeast(int value, String condition) {
		this.matcher = new And(this.matcher, new HasAtLeast(value, condition));
		return this;
	}
	public QueryBuilder hasFewerThan(int value, String condition) {
		this.matcher = new And(this.matcher, new HasFewerThan(value, condition));
		return this;
	}
	
	public QueryBuilder oneOf(Matcher m1, Matcher m2) {
		this.matcher = new And(this.matcher, new Or(m2, m1));
		return this;
	}
}
