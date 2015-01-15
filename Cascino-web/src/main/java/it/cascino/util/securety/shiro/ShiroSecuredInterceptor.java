package it.cascino.util.securety.shiro;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresGuest;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.apache.shiro.subject.Subject;

@Interceptor
@ShiroSecured
public class ShiroSecuredInterceptor implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@AroundInvoke
	public Object interceptShiroSecurity(InvocationContext context) throws Exception{
		Subject subject = SecurityUtils.getSubject();
		Class<?> classe = context.getTarget().getClass();
		Method metodo = context.getMethod();
		
		if(!subject.isAuthenticated() && hasAnnotation(classe, metodo, RequiresAuthentication.class)){
			throw new UnauthenticatedException("Utente Autenticato richiesto");
		}
		
		if(subject.getPrincipal() != null && hasAnnotation(classe, metodo, RequiresGuest.class)){
			throw new UnauthenticatedException("Utente Ospite richiesto");
		}
		
		if(subject.getPrincipal() == null && hasAnnotation(classe, metodo, RequiresUser.class)){
			throw new UnauthenticatedException("Utente richiesto");
		}
		
		RequiresRoles roles = getAnnotation(classe, metodo, RequiresRoles.class);
		
		if(roles != null){
			subject.checkRoles(Arrays.asList(roles.value()));
		}
		
		RequiresPermissions permissions = getAnnotation(classe, metodo, RequiresPermissions.class);
		
		if(permissions != null){
			subject.checkPermissions(permissions.value());
		}
		
		return context.proceed();
	}
	
	private static boolean hasAnnotation(Class<?> classe, Method metodo, Class<? extends Annotation> annotazione){
		return metodo.isAnnotationPresent(annotazione)
		|| classe.isAnnotationPresent(annotazione)
		|| classe.getSuperclass().isAnnotationPresent(annotazione);
	}
	
	private static <Annot extends Annotation> Annot getAnnotation(Class<?> classe, Method metodo, Class<Annot> annotazione){
		return metodo.isAnnotationPresent(annotazione) ? metodo.getAnnotation(annotazione)
		: classe.isAnnotationPresent(annotazione) ? classe.getAnnotation(annotazione)
		: classe.getSuperclass().getAnnotation(annotazione);
	}
	
}