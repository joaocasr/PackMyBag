echo "Generating env-config.js with VUE_API_DOMAIN=$VUE_API_DOMAIN"
cat <<EOF > /usr/share/nginx/html/env-config.js
window.VUE_API_DOMAIN="${VUE_API_DOMAIN}";
EOF