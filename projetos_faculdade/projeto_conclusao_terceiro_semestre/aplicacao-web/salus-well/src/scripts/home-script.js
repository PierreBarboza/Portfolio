var images = ["pessoa1.png", "pessoa2.png", "pessoa3.png", "pessoa4.png", "pessoa5.png", "pessoa6.png"];
var index = 0;

setInterval(function () {

  index++;
  var image = document.getElementById("myImage");

  if (index >= images.length) {
    index = 0;
  }
  image.style.opacity = 0;
  setTimeout(function () {
    image.src = "assets/persons/" + images[index];
    image.style.opacity = 1;

  }, 1000);
}, 6000);


