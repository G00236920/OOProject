package ie.gmit.sw;

public class Shingle {
	
	int hashValue;
	int documentId; 
	
	Shingle(int hashValue, int id) {
		this.hashValue = hashValue;
		this.documentId = id;
	}

	public Shingle() {
		// TODO Auto-generated constructor stub
	}

	public int getDocumentId() {
		return documentId;
	}

	public void setDocumentId(int documentId) {
		this.documentId = documentId;
	}

	public int getHashValue() {
		return hashValue;
	}

	public void setHashValue(int hashValue) {
		this.hashValue = hashValue;
	}
	
}
