<%@ page contentType="text/html;charset=UTF-8" language="java" %> <%@ taglib
uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<form action="login" class="auth" method="post">
  <label class="auth__label" for="record">
    <span>Номер зачетки</span>
    <input placeholder="090302" class="auth__input" minlength="6" maxlength="6" type="text" id="record" name="record" required>
  </label>
  <label class="auth__label" for="password">
    <span>Пароль</span>
    <input placeholder="123456" class="auth__input" type="password" id="password" name="password" required>
  </label>
  <input class="auth__submit" type="submit" value="Войти" />
</form>
