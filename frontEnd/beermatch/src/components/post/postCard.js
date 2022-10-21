import React from "react";
import StarRating from "react-svg-star-rating";

function PostCard({ allPosts, allBusiness }) {
  // const [businessName, setBusinessName] = useState('')
  console.log(allPosts);
  console.log(allBusiness);

  const nameBusiness = (id) => {
    const arrayFiltered = allBusiness.filter((item) => item.id === id);
    return arrayFiltered[0].name;
  };

  console.log(nameBusiness(7));

  return (
    <>
      {allPosts.map((item) => (
        <div className=" m-2 p-2 text-PurpleNavy rounded-lg">
          <div className=" bg-white rounded-lg mx-4 md:mx-auto max-w-md md:max-w-2xl border-2 border-yellow-400 lg:grid lg:grid-cols-2">
            <div className="flex items-start px-4 py-6">
              <img
                className="w-12 h-12 rounded-full object-cover mr-4 shadow"
                src="https://assets.untappd.com/photos/2022_07_14/5615fb8a58ce2b5bc705ab1092d73e53_raw.jpg"
                alt="avatar"
              />
              <div className="">
                <div className="flex items-center justify-between">
                  <h2 className="text-lg font-semibold text-gray-900 -mt-1">
                    {nameBusiness(item.businessId)}
                  </h2>
                </div>
                <p className="text-gray-700">
                  {new Date(item.timestamp).toLocaleDateString("es-ES")}
                </p>
                <p className="mt-3 text-gray-700 text-sm">{item.text}</p>
              </div>
            </div>
            <div className="flex-row m-auto">
              <div className="flex w-full space-x-5 mt-4 my-2  justify-center ">
                <label
                  htmlFor="totalRate"
                  className=" font-semibold text-base "
                >
                  Nota total:
                </label>
                <div className="rating rating-sm justify-center">
                  <StarRating
                    initialRating={item.totalRate}
                    containerClassName="flex"
                    isReadOnly="true"
                    size={25}
                  />
                </div>
              </div>
              <div className="flex w-full space-x-3 my-2 mx-auto   justify-center">
                <label
                  htmlFor="attentionRate"
                  className=" text-sm ml-1 "
                >
                  Nota atencion:
                </label>
                <div className="rating rating-sm justify-center">
                  <StarRating
                    initialRating={item.attentionRate}
                    containerClassName="flex"
                    isReadOnly="true"
                    size={20}
                  />
                </div>
              </div>
              <div className="flex w-full space-x-3 my-2 mx-auto  justify-center">
                <label
                  htmlFor="attentionRate"
                  className="text-sm ml-6"
                >
                  Nota lugar:
                </label>
                <div className="rating rating-sm justify-center">
                  <StarRating
                    initialRating={item.placeRate}
                    containerClassName="flex"
                    isReadOnly="true"
                    size={20}
                  />
                </div>
              </div>
              <div className="flex w-full space-x-3 my-2 mx-auto   justify-center">
                <label
                  htmlFor="attentionRate"
                  className=" text-sm ml-4 "
                >
                  Nota precio:
                </label>
                <div className="rating rating-sm justify-center">
                  <StarRating
                    initialRating={item.priceRate}
                    containerClassName="flex"
                    isReadOnly="true"
                    size={20}
                  />
                </div>
              </div>
              <div className="flex w-full space-x-3 my-2 mx-auto   justify-center">
                <label
                  htmlFor="qualityRate"
                  className=" text-sm ml-3 "
                >
                  Nota calidad:
                </label>
                <div className="rating rating-sm justify-center">
                  <StarRating
                    initialRating={item.qualityRate}
                    containerClassName="flex"
                    isReadOnly="true"
                    size={20}
                  />
                </div>
              </div>
            </div>
          </div>
        </div>
      ))}
    </>
  );
}

export default PostCard;
