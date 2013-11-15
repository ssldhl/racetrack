<%@ page import="racetrack.User" %>

<div class="fieldcontain ${hasErrors(bean: loginInstance, field: 'login', 'error')} required">
	<label for="login">
		<g:message code="user.login.label" default="Login" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="login" required="" value="${loginInstance?.login}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: loginInstance, field: 'password', 'error')} required">
	<label for="password">
		<g:message code="user.password.label" default="Password" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="password" name="password" required="" value="${loginInstance?.password}"/>
</div>