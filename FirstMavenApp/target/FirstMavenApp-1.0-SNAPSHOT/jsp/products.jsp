<head>
    <meta charset="UTF-8">
    <title>Products</title>
</head>
<body>
    <%@ page import="java.util.Set" %>
    <%@ page import="java.util.List" %>
    <%@ page import="com.arobs.domain.ProductDTO" %>

    <form action="products" method="get">

        <h1> All products </h1>
        <table border="1">
            		<thead>
            			<tr>
            				<th>ID</th>
            				<th>Product name</th>
            				<th>Product price</th>
            				<th>Product quantity</th>
            			</tr>
            		</thead>
            		<tbody>
            			<%
            				List<ProductDTO> list = (List) request.getAttribute("allProducts");
            			%>

            			<%
            				for (ProductDTO u : list) {
            			%>
            			<tr>
            				<td><%=u.getItemID()%></td>
            				<td><%=u.getName()%></td>
            				<td><%=u.getPrice()%></td>
            				<td><%=u.getQuantity()%></td>
            			</tr>
            			<%
            				}
            			%>
            		</tbody>
            	</table>
    </form>
    <form action="products" method="post">

               <br>
                    <input type="text" name="productName">
                    <br>
                    <input type="text" name="productQuantity">
                    <br>
                    <input type="submit" value="ADD">
            <h1> My products </h1>
            <table border="1">
                        		<thead>
                        			<tr>
                        				<th>ID</th>
                        				<th>My product name</th>
                        				<th>My product price</th>
                        				<th>My product quantity</th>
                        			</tr>
                        		</thead>
                        		<tbody>
                        			<%
                        				Set<ProductDTO> myList = (Set) request.getAttribute("myProducts");
                        			%>

                        			<%
                        				for (ProductDTO u : myList) {
                        			%>
                        			<tr>
                        				<td><%=u.getItemID()%></td>
                        				<td><%=u.getName()%></td>
                        				<td><%=u.getPrice()%></td>
                        				<td><%=u.getQuantity()%></td>
                        			</tr>
                        			<%
                        				}
                        			%>
                        		</tbody>
                        	</table>
    </form>
</body>