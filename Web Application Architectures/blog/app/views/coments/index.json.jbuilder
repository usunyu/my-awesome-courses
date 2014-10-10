json.array!(@coments) do |coment|
  json.extract! coment, :id, :post_id, :body
  json.url coment_url(coment, format: :json)
end
