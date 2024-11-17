document.getElementById("formulario-login").addEventListener("submit", function(event) {
    event.preventDefault();

    const formData = {
                username: document.getElementById("usuario").value,
                password: document.getElementById("password").value
            };
    const jdata = JSON.stringify(formData);
    console.log(formData);
    console.log(jdata);
    fetch("Login", {
                method: "POST",
                headers: {"Content-Type": "application/json"},
                body: jdata
    })
    .then(res => {
        console.log(res);
        if (res.redirected) 
            window.location.href = res.url; // Redirige al enlace proporcionado
         else 
            console.log("Error o mensaje del servidor:", res.message);
    });
});