package stack;

import java.util.ArrayList;
import java.util.List;

public class OOStack {
    private List<Object> elements = new ArrayList<>();
    private StackElement popOperation = new ExplodingPopOperation();
    private StackElement topOperation = new ExplodingTopOperation();
    static public String stackEmptyErrorDescription = "Stack is empty";

    public boolean isEmpty() {
        return elements.isEmpty();
    }

    public OOStack push(Object element) {
        elements.add(element);
        popOperation = new PopOperation(new NotEmptyPopCondition());
        topOperation = new TopOperation();
        return this;
    }

    public Object basicPop() {
        return elements.remove(elements.size() - 1);
    }

    public Object explodingPop() {
        throw new Error(stackEmptyErrorDescription);
    }

    public Object basicTop() {
        return elements.get(elements.size() - 1);
    }

    public Object explodingTop() {
        throw new Error(stackEmptyErrorDescription);
    }

    public Object pop() {
        return popOperation.execute(this);
    }

    public Object top() {
        return topOperation.execute(this);
    }

    public int size() {
        return elements.size();
    }
}


//
//public class OOStack {
//	static public String stackEmptyErrorDescription = "Stack is empty";
//	public List<Object> elements = new ArrayList<Object>();
//	StackElement se = new SEVacio();
//
//	public boolean isEmpty() {
//		return elements.isEmpty();
//	}
//
//	public OOStack push(String string) {
//		elements.add(string);
//		se = new SENoVacio();
//		return this;
//	}
//
//	public Object pop() {
//	     return se.pop(this);
//
//	}
//
//	public Object explodingPop() {
//		throw new Error(stackEmptyErrorDescription);
//
//	}
//	public Object basicPop() {
//		return elements.remove(elements.size() - 1);
//	}
////	public Object notBasicPop() {
////		return new SEVacio();
////	}
//	
//	public Object top() {
//		return se.top(this);
//		//return elements.get(elements.size()-1);
//	}
////return operation.execute(elements);
//		
//	public Object explodingTop() {
//		throw new Error(stackEmptyErrorDescription);
//	}
//	
//	public Object basicTop() {
//		return elements.get(elements.size()-1);
//	}
//	
//	
//	public int size() {
//		return elements.size();
//	}
//
//
//}

