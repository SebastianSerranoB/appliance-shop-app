package com.appliance_shop_app.sales_service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sales")
public class SaleController {

    //autowired
    //saleService interface


    //findOne


    //getAll

    //cancelOne

    //CompleteOne

    //between 2 dates, top 10 sales, product most sold, highest price sale

    /*
            Obtener una venta por ID
            📌 GET /sales/{saleId}
            Devuelve una venta específica.

            Obtener todas las ventas
            📌 GET /sales
            Devuelve la lista de todas las ventas registradas.

            Completar una venta
            📌 POST /sales/complete
            Recibe el cartId, méthod de pago, datos del consumidor y verifica que el carrito está en estado CHECKED_OUT. Si todo está bien, crea la venta.
            DTOs: El sales-service debería recibir un SaleRequestDTO en el endpoint de completar la venta, que incluya el cartId, el método de pago y datos del consumidor.

            Cancelar una venta
            📌 PUT /sales/{saleId}/cancel
            Cambia el estado de la venta a CANCELED.

            Obtener ventas en un rango de fechas
            📌 GET /sales?startDate={YYYY-MM-DD}&endDate={YYYY-MM-DD}
            Devuelve todas las ventas realizadas en el rango de fechas.

            Obtener las 10 ventas más caras
            📌 GET /sales/top10
            Retorna las 10 ventas de mayor valor.

            Obtener el producto más vendido
            📌 GET /sales/top-product
            Devuelve el producto más vendido según las ventas registradas.

            Obtener la venta de mayor precio
            📌 GET /sales/highest-price
            Devuelve la venta con el precio más alto.

            Comunicación con cart-service: Al completar una venta, el sales-service debería hacer una llamada a cart-service vía Feign para obtener los detalles del carrito y verificar su estado.

Estados de venta: Manejar estados como PENDING, COMPLETED y CANCELED.

Manejo de errores: Si el carrito no está en estado CHECKED_OUT, devolver un 400 Bad Request.

Proceso de completar la venta
El usuario envía un POST /sales/complete con el cartId, método de pago y datos del cliente.

sales-service llama a cart-service (GET /cart/findOne/{cartId}) para obtener los detalles del carrito.

Si el carrito está en estado CHECKED_OUT, se crea una nueva Sale con estado COMPLETED.

Se hace una llamada a cart-service para cambiar el estado del carrito a COMPLETED (PUT /cart/{cartId}/complete).

Se devuelve una respuesta con los datos de la venta.

    */




}
