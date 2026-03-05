var splide = new Splide(".splide", {
    type: "loop",
    padding: "20%",
//    fixedWidth: "100%",
    perMove: 1,
    autoplay: true,
    breakpoints: {
        768: {fixedWidth: '250px'},
        480: {fixedWidth: '90%'} // Mobile thì cho to ra xíu cho dễ nhìn
    }
});
splide.mount();