package stack;

public abstract class StackElement {
    public abstract Object execute(OOStack stack);
}


abstract class PopCondition {
    public abstract boolean check(OOStack stack);
    public abstract Object executePop(OOStack stack);
}

class NotEmptyPopCondition extends PopCondition {
    public boolean check(OOStack stack) {
        return !stack.isEmpty();
    }

    public Object executePop(OOStack stack) {
        return stack.basicPop();
    }
}

class EmptyPopCondition extends PopCondition {
    public boolean check(OOStack stack) {
        return stack.isEmpty();
    }

    public Object executePop(OOStack stack) {
        throw new Error(OOStack.stackEmptyErrorDescription);
    }
}

class ExplodingPopOperation extends StackElement {
    public Object execute(OOStack stack) {
        throw new Error(OOStack.stackEmptyErrorDescription);
    }
}


class PopOperation extends StackElement {
    private PopCondition popCondition;

    public PopOperation(PopCondition popCondition) {
        this.popCondition = popCondition;
    }

    public Object execute(OOStack stack) {
        return popCondition.executePop(stack);
    }
}

class TopOperation extends StackElement {
    public Object execute(OOStack stack) {
        return stack.basicTop();
    }
}

class ExplodingTopOperation extends StackElement {
    public Object execute(OOStack stack) {
        throw new Error(OOStack.stackEmptyErrorDescription);
    }
}


//    public Object execute(OOStack stack) {
//        if (popCondition.check(stack)) {
//            return stack.basicPop();
//        }
//        throw new Error(OOStack.stackEmptyErrorDescription);
//    }
//}




