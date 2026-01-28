var splide = new Splide(".splide", {
  perPage: 4,
  rewind: true,
  perMove: 4,
  type: "loop",
  wheel: true,
  autoplay: true,
  breakpoints: {
    1004: {
      perPage: 4,
    },
    804: {
      perPage: 3,
    },
    604: {
      perPage: 2,
    },
    404: {
      perPage: 1,
    },
  },
});
splide.mount();
