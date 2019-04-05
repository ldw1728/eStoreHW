<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="container-wrapper">
	<div class="container">
		<h1>Update Product</h1>
		<p class="lead">Fill the blew information to add a product:</p>
		
		<c:if test="${fileError == true}">
			 <script>alert("업로드 파일을 다시 확인해주세요");</script>
		</c:if>

		<sf:form
			action="${pageContext.request.contextPath}/admin/productInventory/updateProduct?${_csrf.parameterName}=${_csrf.token}"
			method="POST" modelAttribute="product" enctype="multipart/form-data">
			
			<!-- enctype="multipart/form-data"속성의 경우 csrf토큰을 action속성에 추가해주어야 403오류가 나지않는다. -->
			
			<sf:hidden path="id"/>
			<div class="form-group">
				<label for="name">Name</label>
				<sf:input path="name" id="name" class="form-control" />
				<sf:errors path="name" cssStyle="color:#ff0000;"/>
			</div>

			<div class="form-group">
				<label for="category">Category: </label>
				<sf:radiobutton path="category" id="category" value="컴퓨터" />
				컴퓨터
				<sf:radiobutton path="category" id="category" value="가전" />
				가전
				<sf:radiobutton path="category" id="category" value="잡화" />
				잡화
			</div>

			<div class="form-group">
				<label for="description">Description</label>
				<sf:textarea path="description" id="description"
					class="form-control" />
			</div>

			<div class="form-group">
				<label for="price">Price</label>
				<sf:textarea path="price" id="price" class="form-control" />
				<sf:errors path="price" cssStyle="color:#ff0000;"/>
			</div>

			<div class="form-group">
				<label for="unitInStock">Unit In Stock</label>
				<sf:textarea path="unitInStock" id="unitInStock"
					class="form-control" />
					<sf:errors path="unitInStock" cssStyle="color:#ff0000;"/>
			</div>

			<div class="form-group">
				<label for="manufacturer">Manufacturer</label>
				<sf:textarea path="manufacturer" id="manufacturer"
					class="form-control" />
					<sf:errors path="manufacturer" cssStyle="color:#ff0000;"/>
			</div>
			
			<div class="form-group">
			이미지 업로드:  <input type="file" name="image" accept="image/jpg, image/jpeg, image/png"/><br>
			<span style="font-size:small; font-style:italic">* 5MB이하 및 이미지파일만 업로드 가능합니다.</span>
			</div>
			
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			
			<input type="submit" value="submit" class="btn btn-default"/>
			<a href="<c:url value="/admin/productInventory" />"
				class="btn btn-default">Cancel</a>
		</sf:form>
		<br />
	</div>
</div>