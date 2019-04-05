<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@page import= "javax.imageio.ImageIO" %>
<div class="container-wrapper" style="text-align:center">
<h1>Product Detail</h1>
<h2>Here is the detail information of the product!<h2><br>

	<div class="row">

		<div class="col-md-6">
		<img alt="이미지가 없습니다...." src="${pageContext.request.contextPath}/resources/upload/${fileName}"
			width="400" height="300"/>
		
		</div>

		<div class="col-md-6" style="text-align:left">
			<h3 style="font-weight: bold">${product.name}</h3>
			<p><i>${product.description}</i></p>
		
			<p><span style="font-weight: bold">Manufacturer: </span>${product.manufacturer}</p>
		
			<p><span style="font-weight: bold">Category: </span>${product.category}</p>
			
			<h3>${product.price} 원</h3>

		</div>

	</div>
	
</div>
