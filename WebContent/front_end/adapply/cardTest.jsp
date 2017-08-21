<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>Insert title here</title>


</head>
<body>
    <style>
        .demo-container {
            width: 100%;
            max-width: 350px;
            margin: 50px auto;
        }

        form {
            margin: 30px;
        }
        input {
            width: 200px;
            margin: 10px auto;
	        display: block;
        }

    </style>
    
    <div class="demo-container">
        <div class="card-wrapper"></div>

        <div class="form-container active">
            <form action="">
                <input placeholder="Card number" type="tel" name="number">
                <input placeholder="Full name" type="text" name="name">
                <input placeholder="MM/YY" type="tel" name="expiry">
                <input placeholder="CVC" type="number" name="cvc">
            </form>
        </div>
    </div>

    <script src="<%=request.getContextPath()%>/style/card-master/dist/card.js"></script>
    <script>
        new Card({
            form: document.querySelector('form'),
            container: '.card-wrapper'
            
//             ,placeholders: {
//             	number: '**** **** **** ****',
//             	name: 'Arya Stark',
//             	expiry: '**/****',
//             	cvc: '***'
//            }
        });
        
//         卡號前四碼
//         VISA              4     XXX
//         Master            51~55 XX
//         American Express  340   XX 
//         JCB Card          300   XX
//         DISCOVE           65    XX
    </script>





</body>
</html>