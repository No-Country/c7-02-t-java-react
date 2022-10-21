import React from "react";
import StarRating from "react-svg-star-rating";

function BusinessCard({ allBusiness }) {
  console.log(allBusiness);

  const total = 0;
  const sumTotal = allBusiness.forEach((item) => {
    item.reviewEntitySet.forEach((item) => {
      total += item.totalRate;
    });
  });

  console.log(total);

  if (allBusiness.length > 0)
    return (
      <>
        <div className="lg:flex sm:block  items-center justify-center ">
          {" "}
          {allBusiness.map((item) => (
            <div
              className="rounded-xl border-2 border-yellow-400 p-4 m-2 text-slate-600 shadow-md lg:w-9/12 w-11/12 bg-white"
              key={item.id}
            >
              <div className="flex items-center justify-between border-b pb-3 ">
                <div className="flex items-center space-x-3">
                  <img
                    className="w-12 h-12 rounded-full object-cover mr-4 shadow"
                    src="https://assets.untappd.com/photos/2022_07_14/5615fb8a58ce2b5bc705ab1092d73e53_raw.jpg"
                    alt="avatar"
                  />{" "}
                  <div className="text-lg font-bold ">{item.name}</div>
                </div>
              </div>

              <div className="mt-4 mb-6">
                <div className="mb-2 text-base font-light  ">
                  Ciudad: {item.businessState}
                </div>
                <div className="mb-2 text-base font-light  ">
                  Provincia: {item.businessCity}
                </div>
                <div className="mb-2 text-base font-light  ">
                  Direccion: {item.businessAddress}
                </div>
                <div className="mb-2 text-base font-light  ">
                  Telefono: {item.phone}
                </div>
                <div className="text-sm text-neutral-600">
                  Descripcion: {item.aboutUsText}
                </div>

              </div>

              <div>
                <div className="flex items-center text-slate-500 border-b">
                  <div className="flex space-x-4 md:space-x-16 justify-between mx-auto">
                    <div className="flex cursor-pointer transition hover:text-slate-600">
                      <svg
                        xmlns="http://www.w3.org/2000/svg"
                        className="mr-1.5 h-10 w-10"
                        fill="none"
                        viewBox="0 0 24 24"
                        stroke="currentColor"
                        stroke-width="2"
                      >
                        <path
                          stroke-linecap="round"
                          stroke-linejoin="round"
                          d="M7 8h10M7 12h4m1 8l-4-4H5a2 2 0 01-2-2V6a2 2 0 012-2h14a2 2 0 012 2v8a2 2 0 01-2 2h-3l-4 4z"
                        />
                      </svg>
                      <span className="text-xl">
                        {item.reviewEntitySet.length}
                      </span>
                    </div>
                    <div className="flex cursor-pointer text-center transition hover:text-slate-600">
                      {/* <div className="text-center">
                        <StarRating containerclassName="flex" />
                      </div> */}
                      {item.reviewEntitySet.length > 0 ? (
                        <div>
                          <div className="text-center">
                            <StarRating
                              containerClassName="flex"
                              isReadOnly="true"
                              initialRating={(
                                item.reviewEntitySet
                                  .map((item) => item.totalRate)
                                  .reduce((prev, next) => prev + next) /
                                item.reviewEntitySet.length
                              ).toFixed(2)}
                            />
                          </div>
                          {/* <div>
                            {(
                              item.reviewEntitySet
                                .map((item) => item.totalRate)
                                .reduce((prev, next) => prev + next) /
                              item.reviewEntitySet.length
                            ).toFixed(2)}
                          </div> */}
                        </div>
                      ) : (
                        <div className="text-center">
                          <StarRating
                            containerClassName="flex"
                            isReadOnly="true"
                            initialRating={0}
                          />
                          <span>0</span>
                        </div>
                      )}
                    </div>
                  </div>
                </div>
              </div>
              <div className="flex space-x-6 mx-auto justify-center">
                <a className="mt-2" href="">
                  <img
                    src="/facebook-social-media-svgrepo-com.svg"
                    className="mx-auto w-8"
                    alt="logo"
                  />
                </a>
                <a className="mt-2" href="">
                  <img
                    src="/instagram-svgrepo-com.svg"
                    className="mx-auto w-8"
                    alt="logo"
                  />
                </a>
              </div>
            </div>
          ))}
        </div>
      </>
    );
}

export default BusinessCard;

// {
//   "aboutUsText": "Bar Beer ",
//   "address": "Obispo Oro 457",
//   "city": "Cordoba",
//   "country": "Argentina",
//   "email": "barbeer@mail.com",
//   "idUser": 0,
//   "image": "imagen.jpg",
//   "name": "BARBEER",
//   "phone": 35166552255,
//   "state": "Cordoba",
//   "urlFacebook": "barbeer.facebook.com",
//   "urlInstagram": "barbeer.instagram.com"
// }
