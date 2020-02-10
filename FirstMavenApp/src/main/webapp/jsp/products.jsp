<head>
    <meta charset="UTF-8">
    <title>Products</title>
</head>
<body>
    <%@ page import="java.util.List" %>
    <%@ page import="com.arobs.domain.Product" %>

    <form action="products" method="get">

        <h1> All products </h1>
        <table border="1">
            		<thead>
            			<tr>
            				<th>Product name</th>
            				<th>Product price</th>
            			</tr>
            		</thead>
            		<tbody>
            			<%
            				List<Product> list = (List) request.getAttribute("allProducts");
            			%>

            			<%
            				for (Product u : list) {
            			%>
            			<tr>
            				<td><%=u.getName()%></td>
            				<td><%=u.getPrice()%></td>
            			</tr>
            			<%
            				}
            			%>
            		</tbody>
            	</table>
    </form>

    <form action="products" method="post">

        <h1> My products </h1>
           <br>
                <input type="text" name="productName">
                <br>
                <input type="text" name="productPrice">
                <br>
                <input type="submit" value="ADD">

        <table border="1">
                    		<thead>
                    			<tr>
                    				<th>Product name</th>
                    				<th>Product price</th>
                    			</tr>
                    		</thead>
                    		<tbody>
                    			<%
                    				List<Product> myProducts = (List) request.getAttribute("myProducts");
                    				if(myProducts!=null){
                    			%>

                    			<%
                    				for (Product u : myProducts) {
                    			%>
                    			<tr>
                    				<td><%=u.getName()%></td>
                    				<td><%=u.getPrice()%></td>
                    			</tr>
                    			<%
                    				}}
                    			%>
                    		</tbody>
                    	</table>

    </form>
</body>