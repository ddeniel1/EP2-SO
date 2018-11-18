loge <- log_3
linear.model <- glm(loge$Tempo ~ loge$Leitores)
#### Create Plot Data (Predicted Values Using Omnibus Model) ####
x <- loge$Leitores#seq(from = max(loge$Leitores), to = 0)
y <- loge$Tempo#array(NA, dim = length(x))
#y.upper <- y
#y.lower <- y
#(for (i in 1:length(x)) {
#  #raw.y <- coef(summary(update(linear.model, . ~ . - loge$Leitores + eval(loge$Leitores - 
#                                                                            x[i]))))[1, 1]
#  raw.se <- coef(summary(update(linear.model, . ~ . - loge$Leitores + eval(loge$Leitores - 
#                                                                             x[i]))))[1, 2]
#  y[i] <- raw.y
#  y.upper[i] <- raw.y + raw.se
#  y.lower[i] <- raw.y - raw.se
#}

#### Create Plot ####
op <- par(cex.main = 1.5, mar = c(5, 6, 4, 5) + 0.1, mgp = c(3.5, 1, 0), cex.lab = 1.5, 
          font.lab = 2, cex.axis = 1.5, bty = "n", las = 1)
plot(loge$Leitores, loge$Tempo, xlab = "", ylab = "", type = "", xlim = c(max(x),0), ylim = c(0, max(y)), axes = FALSE,main = "Implementação 3")
axis(1)
axis(2)
#polygon(c(x, rev(x)), c(y.upper, rev(y.lower)), col = "lightsteelblue", border = NA)
lines(x, y, lwd = 2)
mtext("Leitores", side = 1, line = 2.5, cex = 1.5)
mtext("Tempo", side = 2, line = 3.7, cex = 1.5, las = 0)