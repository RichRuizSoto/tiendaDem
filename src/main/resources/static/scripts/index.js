function crearBoton(texto) {
    const boton = document.createElement('button');
    boton.className = 'contenido-btn';
    boton.textContent = texto;
    return boton;
}

function mostrarContenido(opcion) {
    const contenedor = document.getElementById(`div-${opcion}`);
    contenedor.hidden = !contenedor.hidden;
    contenedor.innerHTML = '';

    const botonesPorOpcion = {
        featured: ["Extra Comfort Shoes", "Chunky & Retro Shoes", "Customize Our Shoes", "Slip On Shoes", "Mary Jane Shoes", "Platform Shoes", "Skate Shoes", "Sandals and Slides", "Offers & Promotions", "Gift Cards"],
        icons: ["Old Skool", "Authentic", "Classic Slip-On", "Sk8-Hi", "Half Cab"],
        shop: ["Checkerboard", "White", "Black", "Pink", "Blue"],
        sports: ["Skateboarding", "Surf", "BMX", "Snowboarding"],
        support: ["Help Center", "FAQ", "Order Status", "Shipping", "Returns", "Size Guides", "Find a Store", "Learn About Vans Family", "Contact Us"],
        about: ["Our Story", "Sustainability", "Careers"]
    };

    botonesPorOpcion[opcion]?.forEach(texto => {
        contenedor.appendChild(crearBoton(texto));
        contenedor.appendChild(document.createElement('br'));
    })
}




