package hr.abysalto.hiring.mid.product.api;

public final class ProductPayloadExamples {

    public static final String GET_PRODUCTS_RESPONSE = """
            {
                "products": [
                    {
                        "id": "XQ98P5Z6KYV4D3LM",
                        "title": "Essence Mascara Lash Princess"
                        "description": "The Essence Mascara Lash Princess is a popular mascara known for its volumizing and lengthening effects. Achieve dramatic lashes with this long-lasting and cruelty-free formula.",
                        "brand": "Essence",
                        "pricing": {
                            "price": 9.99,
                            "discountPercentage": 7.17
                        },
                        "productSpecification": {
                            "width": 23.17,
                            "height": 14.43,
                            "depth": 28.01,
                            "weight": 2,
                            "barcode": "9164035109868",
                            "qrCode": "...",
                            "category": "beauty",
                            "tags": [
                                "beauty",
                                "mascara"
                            ],
                            "rating": 4.94,
                            "thumbnail": "...",
                            "images": ["...", "...", "..."]
                        },
                        "purchasePolicy": {
                            "warrantyInformation": "1 month warranty",
                            "shippingInformation": "Ships in 1 month",
                            "availabilityStatus": "Low Stock",
                            "returnPolicy": "30 days return policy",
                            "minimumOrderQuantity": 24,
                        },
                        "reviews": [
                            {
                                "rating": 2,
                                "comment": "Very unhappy with my purchase!",
                                "date": "2024-05-23T08:56:21.618Z",
                                "reviewerName": "John Doe",
                                "reviewerEmail": "john.doe@x.dummyjson.com"
                            }
                        ],
                    }
                ],
                "total": 1,
                "page": 1,
                "size": 20
            }
            """;

    public static final String GET_PRODUCT_RESPONSE = """
            {
                    "id": "XQ98P5Z6KYV4D3LM",
                    "title": "Essence Mascara Lash Princess"
                    "description": "The Essence Mascara Lash Princess is a popular mascara known for its volumizing and lengthening effects. Achieve dramatic lashes with this long-lasting and cruelty-free formula.",
                    "brand": "Essence",
                    "pricing": {
                        "price": 9.99,
                        "discountPercentage": 7.17
                    },
                    "productSpecification": {
                        "width": 23.17,
                        "height": 14.43,
                        "depth": 28.01,
                        "weight": 2,
                        "barcode": "9164035109868",
                        "qrCode": "...",
                        "category": "beauty",
                        "tags": [
                            "beauty",
                            "mascara"
                        ],
                        "rating": 4.94,
                        "thumbnail": "...",
                        "images": ["...", "...", "..."]
                    },
                    "purchasePolicy": {
                        "warrantyInformation": "1 month warranty",
                        "shippingInformation": "Ships in 1 month",
                        "availabilityStatus": "Low Stock",
                        "returnPolicy": "30 days return policy",
                        "minimumOrderQuantity": 24,
                    },
                    "reviews": [
                        {
                            "rating": 2,
                            "comment": "Very unhappy with my purchase!",
                            "date": "2024-05-23T08:56:21.618Z",
                            "reviewerName": "John Doe",
                            "reviewerEmail": "john.doe@x.dummyjson.com"
                        }
                    ],
                }
            """;
}
