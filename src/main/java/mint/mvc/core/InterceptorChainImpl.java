package mint.mvc.core;

import java.util.List;

/**
 * Used for holds an interceptor chain.
 * @author Michael Liao (askxuefeng@gmail.com)
 */
class InterceptorChainImpl implements InterceptorChain {
    private final 	List<Interceptor> interceptors;
    private int 	index 	= 0;
    private boolean isPass 	= false;
    private	int size;
    
    boolean isPass() {
        return isPass;
    }
    
    InterceptorChainImpl(List<Interceptor> interceptors) {
    	this.interceptors = interceptors;
    	size = this.interceptors.size();
    }

    public void doInterceptor(ActionContext ctx) throws Exception {
        if(index == size){
        	this.isPass = true;
        } else {
            //must update index first, otherwise will cause stack overflow:
            index++;
            interceptors.get(index-1).intercept(ctx, this);
        }
    }
}